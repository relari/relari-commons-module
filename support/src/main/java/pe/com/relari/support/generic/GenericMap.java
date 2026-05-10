package pe.com.relari.support.generic;

import java.util.HashMap;

/**
 * Class: GenericMap.
 *
 * @param <K> Key
 * @param <V> Value
 */

public class GenericMap<K, V> extends HashMap<K, V> {

  public V getValue(K key) {
    return super.get(key);
  }

}
