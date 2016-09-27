package category.container.hash;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class Info {

    String city;

    int hostId;

    int listId;

    double score;

    Info(int hostId, int listId, double score, String city) {
        this.city = city;
        this.hostId = hostId;
        this.listId = listId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "[host:" + hostId + " list:" + listId + "]";
    }

}

/**
 * [Air*bnb onsite] 实现分页显示。给了以下一些输入数据，要求将以下行分页显示，每页12行，其中每行已经按score排好序，分页显示的时候如果有相同host id的行，则将后面同host id的行移到下一页。 [
 * "host_id,listing_id,score,city", "1,28,300.1,SanFrancisco", "4,5,209.1,SanFrancisco", "20,7,208.1,SanFrancisco",
 * "23,8,207.1,SanFrancisco", "16,10,206.1,Oakland", "1,16,205.1,SanFrancisco", "6,29,204.1,SanFrancisco",
 * "7,20,203.1,SanFrancisco", "8,21,202.1,SanFrancisco", "2,18,201.1,SanFrancisco", "2,30,200.1,SanFrancisco",
 * "15,27,109.1,Oakland", "10,13,108.1,Oakland", "11,26,107.1,Oakland", "12,9,106.1,Oakland", "13,1,105.1,Oakland",
 * "22,17,104.1,Oakland", "1,2,103.1,Oakland", "28,24,102.1,Oakland", "18,14,11.1,SanJose", "6,25,10.1,Oakland",
 * "19,15,9.1,SanJose", "3,19,8.1,SanJose", "3,11,7.1,Oakland", "27,12,6.1,Oakland", "1,3,5.1,Oakland",
 * "25,4,4.1,SanJose", "5,6,3.1,SanJose", "29,22,2.1,SanJose", "30,23,1.1,SanJose" ]
 */
public class PaginatedHotelInfo {

    private static final int PAGE_SIZE = 10;

    private static void addPageToResult(List<List<Info>> result, LinkedHashMap<Integer, Info> currentPage) {
        List<Info> currentList = new ArrayList<Info>(currentPage.size());
        for (Info info : currentPage.values()) {
            currentList.add(info);
        }

        result.add(currentList);
    }

    private static boolean addToPage(List<List<Info>> result, List<LinkedHashMap<Integer, Info>> incomplete, Info info) {
        ListIterator<LinkedHashMap<Integer, Info>> iterator = incomplete.listIterator();

        while (iterator.hasNext()) {
            LinkedHashMap<Integer, Info> currentPage = iterator.next();
            if (!currentPage.containsKey(info.hostId)) {
                currentPage.put(info.hostId, info);

                if (currentPage.size() == PAGE_SIZE) {
                    addPageToResult(result, currentPage);
                    iterator.remove();
                }
                return true;
            }
        }

        return false;
    }

    public static List<List<Info>> paginatedInfos(List<String> infos) {
        List<List<Info>> result = new ArrayList<List<Info>>();

        LinkedHashMap<Integer, Info> hostId2Info = new LinkedHashMap<Integer, Info>();
        List<LinkedHashMap<Integer, Info>> incomplete = new LinkedList<LinkedHashMap<Integer, Info>>();
        incomplete.add(hostId2Info);

        for (String rawInfo : infos) {
            String[] splitted = rawInfo.split(",");
            int hostId = Integer.parseInt(splitted[0]);
            int listId = Integer.parseInt(splitted[1]);
            double score = Double.parseDouble(splitted[2]);
            String city = splitted[3];
            Info info = new Info(hostId, listId, score, city);

            if (!addToPage(result, incomplete, info)) {
                LinkedHashMap<Integer, Info> newPage = new LinkedHashMap<Integer, Info>();
                incomplete.add(newPage);
                newPage.put(hostId, new Info(hostId, listId, score, city));
            }
        }

        for (LinkedHashMap<Integer, Info> currentPage : incomplete) {
            addPageToResult(result, currentPage);
        }

        return result;
    }
}
