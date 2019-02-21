package com.company;

public interface Serializable {

    String toStorableString(Object object);
    Object fromStorableString(String string);
}
