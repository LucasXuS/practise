package mindview.thinkinginjava.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapData<K, V> {
    public static <K, V> Map<K, V> map(List<K> list, V v) {
        Map<K, V> map = new LinkedHashMap<>();
        for (K k : list) {
            map.put(k, v);
        }
        return map;
    }
}
