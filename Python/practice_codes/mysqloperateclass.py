import MySQLdb
class db():
    
    CURSOR_DICT = 0;
    CURSOR_DEFAULT = 1;
    def __init__(self, *args, **kwargs):
        self.__connect = None;
        self.__args = args;
        self.__kwagrs = kwargs;
        
    def queryAll(self, sql, *args):
        cursor = self.__query(db.CURSOR_DICT, sql, *args);
        data = cursor.fetchall();
        cursor.close();
        return data;
    
    def queryRow(self, sql, *args):
        cursor = self.__query(db.CURSOR_DICT, sql, *args);
        data = cursor.fetchone();
        cursor.close();
        return data;
    
    def querySize(self, size, sql, *args):
        cursor = self.__query(db.CURSOR_DICT, sql, *args);
        data = cursor.fetchmany(size);
        cursor.close();
        return data;
    
    def queryColumn(self, sql, *args):
        cursor = self.__query(db.CURSOR_DEFAULT, sql, *args);
        data = cursor.fetchall();
        cursor.close();
        if not data:
            return None;
        return tuple([row[0] for row in data]);
    
    def queryScalar(self, sql, *args):
        cursor = self.__query(db.CURSOR_DEFAULT, sql, *args);
        data = cursor.fetchone();
        cursor.close();
        if not data:
            return None;
        return data[0];
    
    def __query(self, cursorClass, sql, *args):
        connect = self.__getConnect();
        if cursorClass == db.CURSOR_DICT:
            cursor = connect.cursor(MySQLdb.cursors.DictCursor);
        else:
            cursor = connect.cursor();
            cursor.execute(sql, *args);
        return cursor;
    
    def execute(self, sql, *args):
        connect = self.__getConnect();
        cursor = connect.cursor();
        try:
            cursor.execute(sql, *args);
            connect.commit();
            return True;
        except:
            connect.rollback();
            return False;
        finally:
            cursor.close();
            
    def transations(self, sqlArray, *args):
        if len(sqlArray) <= 0:
            return False;
        connect = self.__getConnect();
        cursor = connect.cursor();
        try:
            for index, sql in enumerate(sqlArray):
                arg = args[index] if index in args else ();
                cursor.execute(sql, *arg);
                connect.commit();
            return True;
        except:
            connect.rollback();
            return False;
        finally:
            cursor.close();
            
    def __getConnect(self):
        if self.__connect:
            return self.__connect;
        self.__connect = MySQLdb.Connect(
            *self.__args, **self.__kwagrs
            );
        return self.__connect;
    
    def __del__(self):
        if self.__connect:
            self.__connect.close();