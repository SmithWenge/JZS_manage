package com.jzs.function.support.dictionary;

import java.util.List;

public interface IDictionaryRepository {
    Dictionary selectById(String id);
    List<Dictionary> selectAll();
}
