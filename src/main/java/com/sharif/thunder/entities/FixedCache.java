/*
 * Copyright 2019-2020 SharifPoetra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sharif.thunder.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FixedCache<K, V> {
  private final Map<K, V> map;
  private final K[] keys;
  private int currIndex = 0;

  @SuppressWarnings("unchecked")
  public FixedCache(int size) {
    this.map = new HashMap<>();
    if (size < 1) throw new IllegalArgumentException("Cache size must be at least 1!");
    this.keys = (K[]) new Object[size];
  }

  public V put(K key, V value) {
    if (map.containsKey(key)) {
      return map.put(key, value);
    }
    if (keys[currIndex] != null) {
      map.remove(keys[currIndex]);
    }
    keys[currIndex] = key;
    currIndex = (currIndex + 1) % keys.length;
    return map.put(key, value);
  }

  public V pull(K key) {
    return map.remove(key);
  }

  public V get(K key) {
    return map.get(key);
  }

  public boolean contains(K key) {
    return map.containsKey(key);
  }

  public Collection<V> getValues() {
    return map.values();
  }
}
