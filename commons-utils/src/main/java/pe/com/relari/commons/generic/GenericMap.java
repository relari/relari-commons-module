package pe.com.relari.commons.generic;

import java.util.HashMap;

/**
 * <b>Class:</b> GenericMap.<br>
 *
 * @param <K> Key
 * @param <V> Value
 */

public class GenericMap<K, V> extends HashMap<K, V> {

  public V getValue(K key) {
    return super.get(key);
  }

}
