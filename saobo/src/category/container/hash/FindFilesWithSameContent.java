package category.container.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [Dropbox] Get all files that have the same content in a file system.
 */
public class FindFilesWithSameContent {

    public static void main(String[] args) {
        Folder root = new Folder("root");
        List<String> rootFiles = Arrays.asList("afile1", "bfile2", "cfiles3");
        root.files = rootFiles;

        Folder firstChild = new Folder("first");
        List<String> files1 = Arrays.asList("achild1", "bchild2");
        firstChild.files = files1;

        root.folders = Arrays.asList(firstChild);

        Folder secondChild = new Folder("second");
        List<String> files2 = Arrays.asList("achild4", "cchild3");
        secondChild.files = files2;

        firstChild.files = files1;
        firstChild.folders = Arrays.asList(secondChild);

        List<List<String>> result = new FindFilesWithSameContent().find(root);

        for (List<String> list : result) {
            for (String path : list) {
                System.out.print(path + " ");
            }
            System.out.println();
        }

    }

    private List<List<String>> classifyFiles(List<String> files) {
        boolean[] visited = new boolean[files.size()];

        List<List<String>> result = new ArrayList<List<String>>();

        for (int i = 0; i < files.size(); i++) {
            if (visited[i]) {
                continue;
            }

            List<String> sameFiles = new ArrayList<String>();
            String file = files.get(i);
            sameFiles.add(file);
            visited[i] = true;

            for (int j = i + 1; j < files.size(); j++) {
                if (compare(file, files.get(j))) {
                    visited[j] = true;
                    sameFiles.add(files.get(j));
                }
            }
            result.add(sameFiles);
        }

        return result;
    }

    private boolean compare(String path1, String path2) {
        // replace with real implementation of compare two file byte content.
        return path1.charAt(path1.length() - 1) == path2.charAt(path2.length() - 1);
    }

    public List<List<String>> find(Folder root) {
        if (root == null) {
            throw new RuntimeException("root cannot be null");
        }

        List<List<String>> result = new ArrayList<List<String>>();

        Map<String, List<String>> filesMap = getFilesMap(root);

        for (String hash : filesMap.keySet()) {
            result.addAll(classifyFiles(filesMap.get(hash)));
        }

        return result;
    }

    private void getFileHashHelper(Folder folder, String workingDirectory, Map<String, List<String>> filesMap) {
        for (String file : folder.files) {
            String path = workingDirectory + "/" + file;
            String hash = getHash(path);

            if (filesMap.containsKey(hash)) {
                filesMap.get(hash).add(path);
            } else {
                List<String> pathes = new ArrayList<String>();
                pathes.add(path);
                filesMap.put(hash, pathes);
            }
        }

        for (Folder childFolder : folder.folders) {
            getFileHashHelper(childFolder, workingDirectory + "/" + childFolder.name, filesMap);
        }
    }

    public Map<String, List<String>> getFilesMap(Folder root) {
        Map<String, List<String>> filesMap = new HashMap<String, List<String>>();

        getFileHashHelper(root, "", filesMap);

        return filesMap;
    }

    private String getHash(String path) {
        // replace with hash function against the content.
        return String.valueOf(path.indexOf(0));
    }
}

class Folder {

    List<String> files;

    List<Folder> folders;

    String name;

    public Folder(String name) {
        this.name = name;
        this.files = new ArrayList<String>();
        this.folders = new ArrayList<Folder>();
    }
}
