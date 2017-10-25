#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <errno.h>

#include <sys/stat.h>
#include <sys/types.h>
#include <sys/time.h>

#include <math.h>
//#include <alsa/iatomic.h>

Usage(char *prog)
{
	printf("Usage : %s [creat|link|rmlink|unlink|stat|setattr|setxattr|noop] <rootdir> <depth> <breadth> <filect> <threadct> <blkct> <fullblk> <dosync> <randomize>\n", prog);
	exit(-1);
}

unsigned int 	depth, breadth, blkct, filect, fullblk, dosync, thrct, totalfiles, randomize;
unsigned int	fileidx;
char		    buf[4096];
unsigned int	docreat = 1;

int file_creat(char *pathname);
int file_link(char *pathname);
int file_rmlink(char *pathname);
int file_unlink(char *pathname);
int file_setattr(char *pathname);
int file_setxattr(char *pathname);
int file_stat(char *pathname);
int file_noop(char *pathname);

int (*file_op)(char *pathname);

#define		dir_format 	"dir.%06d/"
#define		fil_format 	"fil.%09d"

void err_out(char *syscall, char *object)
{
	char		buffer[256];
	getcwd(buffer, sizeof(buffer)-1);
	printf("%s %s failed in directory %s : %d\n", syscall, object, buffer, errno);
	exit(-1);
}

#define MAXDEPTH 	8

struct _thr_data {
	unsigned int	idx;
	unsigned int	ct;
	char		buffer[256];
} *thr_data;

void getpath(char *pathname, unsigned int index)
{
	int		mydepth = depth, i;
	char		*ptr = pathname;
	unsigned int	breadthidx[MAXDEPTH], breadthct = 0;

	breadthidx[breadthct++] = index % filect;
	index /= filect;

	while (mydepth--) {
		breadthidx[breadthct++] = index % breadth;
		index /= breadth;
	}

	*ptr = 0;
	for(i=breadthct; i>1; --i) {
		sprintf(ptr, dir_format, breadthidx[i-1]);
		ptr += strlen(ptr);
	}
	sprintf(ptr, fil_format, breadthidx[0]);
}

int file_creat(char *pathname)
{
	int	fd, i;

	fd = open(pathname, O_CREAT | O_WRONLY);
	if( fd < 0 ) {
		return -1;
	}
	for(i=0; i<blkct; i++) {
		if(fullblk) {
			write(fd, buf, 4096);
		} else {
			lseek(fd, 4095, SEEK_CUR);
			write(fd, &buf, 1);
		}
	}
	if( dosync ) fsync(fd);
//	close(fd);
	return 0;
}

int file_link(char *pathname)
{
	char 	pathname1[256];

	strcpy(pathname1, pathname);
	strcat(pathname1, ".link");

	if( link(pathname, pathname1) < 0 ) {
		return -1;
	}

	return 0;
}

int file_rmlink(char *pathname)
{
	char 	pathname1[256];

	strcpy(pathname1, pathname);
	strcat(pathname1, ".link");

	if( unlink(pathname1) < 0 ) {
		return -1;
	}

	return 0;
}

int file_unlink(char *pathname)
{
    char *ptr = NULL;

	if( unlink(pathname) < 0 ) {
        *ptr = 'y';
		return -1;
	}

	return 0;
}

int file_stat(char *pathname)
{
    struct stat     st_buf;

	if( stat(pathname, &st_buf) < 0 ) {
		return -1;
	}

	return 0;
}

int file_setattr(char *pathname)
{
	if( chmod(pathname, S_IRUSR|S_IWUSR|S_IXUSR) < 0 ) {
		return -1;
	}

	return 0;
}

int file_setxattr(char *pathname)
{
    char *value = "value.x";

    if( setxattr(pathname, "user.x", value, strlen(value), 0) < 0 ) {
		return -1;
	}

	return 0;
}

int file_noop(char *pathname)
{
	return 0;
}

void file_ops()
{
	char 	fname[32];
	int	i;

	for(i=0; i<filect; i++)
	{
		sprintf(fname, fil_format, i);
		file_op(fname);
	}
}

void mkdirs(int curd)
{
	char 	dirname[32];
	int 	i;

	for(i=0; i<breadth; i++) {
		if( curd != depth ) {
			sprintf(dirname, dir_format, i);
			if( (file_op == file_creat) && mkdir(dirname, 0777) < 0 && errno != EEXIST ) {
				err_out("mkdir", dirname);
			}
			if( chdir(dirname) < 0 ) {
				err_out("chdir", dirname);
			}
			mkdirs(curd+1);
			chdir("..");
		} else if( !thrct ) {
			file_ops();
		}
	}
}

#define RAND_RANGE 32768
int rand_indexes[RAND_RANGE];
int rand_bit_array[RAND_RANGE/sizeof(int)];

static inline void rand_set_bit(int *bitarr, int idx)
{
    int elem_size = sizeof(bitarr[0]);
    int bit_pos = (idx % elem_size);
    int bit_idx = idx / elem_size;

    bitarr[bit_idx] |= (1U << bit_pos);
}

static inline int rand_get_bit(int *bitarr, int idx)
{
    int elem_size = sizeof(bitarr[0]);
    int bit_pos = idx % elem_size;
    int bit_idx = idx / elem_size;

    return bitarr[bit_idx] & (1U << bit_pos);
}

int rand_gen_indexes()
{
    int i, randnum, range;

    if (!randomize) {
        return;
    }

    range = (totalfiles < RAND_RANGE) ? totalfiles : RAND_RANGE;

    for(i=0; i<range; i++) {
        randnum = rand() % range;
        if( rand_get_bit(rand_bit_array, randnum) ) {
            --i;
            continue;
        }
        rand_set_bit(rand_bit_array, randnum);
        rand_indexes[i] = randnum;
        //printf("rand = %u\n", randnum);
    }
}

int next_file_index()
{
    int nonrand_fileidx, rand_fileidx, range;

    range = (totalfiles < RAND_RANGE) ? totalfiles : RAND_RANGE;

    rand_fileidx = nonrand_fileidx = __sync_add_and_fetch(&fileidx, 1) - 1;

    // We try to randomize the file index within a range
    if (randomize && (nonrand_fileidx < totalfiles)) {
        rand_fileidx = rand_indexes[nonrand_fileidx % range] + (nonrand_fileidx / range) * range;
    }

    return rand_fileidx;
}

void *thrfn(void *arg)
{
	int			        myidx = (int)arg;
	struct _thr_data	*mydata = thr_data+myidx;
	unsigned int		myfileidx;

	mydata->idx = myidx;
	mydata->ct  = 0;

	while(1) {
		myfileidx = next_file_index();
		if( myfileidx >= totalfiles ) {
			break;
        }
		/*else if( (myfileidx % 100) == 0 )
			printf("[INF] {Thr:%d} {Idx:%u} Processing\n", myidx, myfileidx);*/
	//	getpath(mydata->buffer, myfileidx);
		getpath(mydata->buffer, 1);
		if( file_op(mydata->buffer) < 0 ) {
			printf("[ERR] {Thr:%d} {Idx:%u} %s failed\n", myidx, myfileidx, mydata->buffer);
			break;
		} else {
			//printf("[DBG] {Thr:%d} {Idx:%u} %s created\n", myidx, myfileidx, mydata->buffer);
		}
	}
}

main(int argc, char *argv[])
{
	int 		i=1;
	char 		*mode;
	char 		*rootdir;
	pthread_t	*tid;

	if( argc<11 ) {
		Usage(argv[0]);
	}

	mode = argv[i]; i++;
	rootdir = argv[i]; i++;
	depth = atoi(argv[i]); i++;
	breadth = atoi(argv[i]); i++;
	filect = atoi(argv[i]); i++;
	thrct = atoi(argv[i]); i++;
	blkct = atoi(argv[i]); i++;
	fullblk = atoi(argv[i]); i++;
	dosync = atoi(argv[i]); i++;
	randomize = atoi(argv[i]); i++;

	printf("mode = %s\n", mode);
	printf("rootdir = %s\n", rootdir);
	printf("depth = %d\n", depth);
	printf("breadth = %d\n", breadth);
	printf("filect = %d\n", filect);
	printf("thrct = %d\n", thrct);
	printf("blkct = %d\n", blkct);
	printf("fullblk = %d\n", fullblk);
	printf("dosync = %d\n", dosync);
	printf("randomize = %d\n", randomize);

	totalfiles = pow(breadth, depth)*filect;
    if( totalfiles > RAND_RANGE ) {
        totalfiles = (totalfiles / RAND_RANGE) * RAND_RANGE;
    }
    rand_gen_indexes();

	if( !strcmp(mode, "creat") ) {
		file_op = file_creat;
	} else if( !strcmp(mode, "link") ) {
		file_op = file_link;
	} else if( !strcmp(mode, "rmlink") ) {
		file_op = file_rmlink;
	} else if( !strcmp(mode, "unlink") ) {
		file_op = file_unlink;
	} else if( !strcmp(mode, "stat") ) {
		file_op = file_stat;
	} else if( !strcmp(mode, "setattr") ) {
		file_op = file_setattr;
	} else if( !strcmp(mode, "setxattr") ) {
		file_op = file_setxattr;
	} else if( !strcmp(mode, "noop") ) {
		file_op = file_noop;
	} else {
		Usage(argv[0]);
	}

	// Create rootdir
	if( (file_op == file_creat) && mkdir(rootdir, 0777) < 0 && errno != EEXIST ) {
		printf("Failed to create dir %s : %d\n", rootdir, errno);
		exit(-1);
	}
	if( chdir(rootdir) < 0) {
		printf("Failed to chdir to dir %s : %d\n", rootdir, errno);
		exit(-1);
    }

	// Create dirtree
	mkdirs(0);

	if( !thrct ) {
		exit(0);
	}

	printf("Created dirtree. Starting threads to create files ...\n");

	// Create files
	thr_data = malloc(sizeof(*thr_data)*thrct);
	tid = malloc(sizeof(*tid)*thrct);
	if( !thr_data || !tid ) {
		printf("Memory allocation for thr_data failed\n");
		exit(-1);
	}
	for(i=0; i<thrct; i++) {
		pthread_create(&tid[i], NULL, thrfn, i);
	}
	for(i=0; i<thrct; i++) {
	    printf("Thread %d is done\n", i);
            //		pthread_join(tid[i], NULL);
	}

    exit(0);
}
