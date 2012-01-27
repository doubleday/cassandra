package org.apache.cassandra.cache;
/*
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 */


import org.apache.cassandra.config.CFMetaData;
import org.apache.cassandra.db.ColumnFamilyStore;
import org.apache.cassandra.db.DecoratedKey;

import java.nio.ByteBuffer;

public class AutoSavingSSTCache<K extends DecoratedKey, V> extends AutoSavingCache<K, V>
{
    public AutoSavingSSTCache(ICache<K, V> cache, String tableName, String cfName)
    {
        super(cache, tableName, cfName, ColumnFamilyStore.CacheType.SST_CACHE_TYPE);
    }

    @Override
    public double getConfiguredCacheSize(CFMetaData cfm)
    {
        return cfm == null || !CFMetaData.USE_SSTABLE_CACHE ? CFMetaData.DEFAULT_ROW_CACHE_SIZE : cfm.getRowCacheSize();
    }

    @Override
    public ByteBuffer translateKey(K key)
    {
        return key.key;
    }
}
