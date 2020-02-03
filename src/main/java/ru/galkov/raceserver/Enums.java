package ru.galkov.raceserver;

public interface Enums {
	public static enum fieldsJSON {registred_race_id,registred_start_id,point_type,stop_time,start_time,IMGType,counter,topic,master_mark_delta,mark_master_latitude,mark_master_altitude,mark_master_longitude, master_mark_label, mark_type,resultsFileDir,resultsFileLink, exec_login,exec_level,start, asker,password, rows, date, key, mark, marks, error, usersArr, login, level, status, latitude, altitude,longitude, label, race_id, gps_point_id, race, race_name, start_id, start_label, mark_label, name, active, racesConfig, startsConfig, fileType}
	public static enum trigger {TRUE, FALSE}
	public static enum img_types {ALL, LOGIN}
	public static enum access {Admin, User, Guest, Error,Delete}
    public enum points_types {mark,master_mark,user,guest,admin,unknown}
    public enum fileType {Results, Marcs, Log}
    public enum marksTypes {master, normal}
}
