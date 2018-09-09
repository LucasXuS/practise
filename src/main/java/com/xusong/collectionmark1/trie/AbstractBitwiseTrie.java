package com.xusong.collectionmark1.trie;

import com.xusong.collectionmark1.Trie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;


// 这是一个针对KeyAnalyzer的装饰器模式。
public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V>
        implements Trie<K, V>, Serializable {

    private final KeyAnalyzer<? super K> keyAnalyzer;

    protected AbstractBitwiseTrie(final KeyAnalyzer<? super K> keyAnalyzer) {
        if (keyAnalyzer == null) {
            throw new NullPointerException("keyAnalyzer shouldn't be true.");
        }
        this.keyAnalyzer = keyAnalyzer;
    }

    KeyAnalyzer<? super K> getKeyAnalyzer() {
        return keyAnalyzer;
    }


    abstract static class BasicEntry<K, V> implements Map.Entry<K, V>, Serializable {
        private K key;
        private V value;

        public BasicEntry(final K key) {
            this.key = key;
        }

        public BasicEntry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public V setKeyValue(final K key, final V value) {
            this.key = key;
            return setValue(value);
        }

        @Override
        public V setValue(final V value) {
            final V previous = this.value;
            this.value = value;
            return previous;
        }

        @Override
        public int hashCode() {
            int keyCode = getKey() == null ? 0 : getKey().hashCode();
            int valueCode = getKey() == null ? 0 : getValue().hashCode();
            return keyCode ^ valueCode;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof BasicEntry)) {
                return false;
            }
            BasicEntry<?, ?> other = (BasicEntry<?, ?>) o;
            return other.getKey().equals(getKey()) && other.getValue().equals(getValue());
        }
    }
}
