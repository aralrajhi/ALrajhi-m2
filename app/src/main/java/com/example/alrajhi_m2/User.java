package com.example.alrajhi_m2;

public class User {
    long _rowId;
    String _Id;
    String _NationalId;
    String _firstName;
    String _Surname;


    public long get_rowId() {
        return _rowId;
    }

    public void set_rowId(long _rowId) {
        this._rowId = _rowId;
    }

    public String get_Id() {
        return _Id;
    }

    public void set_Id(String _Id) {
        this._Id = _Id;
    }

    public String get_NationalId() {
        return _NationalId;
    }

    public void set_NationalId(String _NationalId) {
        this._NationalId = _NationalId;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_Surname() {
        return _Surname;
    }

    public void set_Surname(String _Surname) {
        this._Surname = _Surname;
    }
}
