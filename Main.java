package GameProgress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main ( String[] args ) {
        SaveGame saveGame1 = new SaveGame ( 100 , 5 , 1 , 1.2D );
        SaveGame saveGame2 = new SaveGame ( 50 , 4 , 52 , 1.3D );
        SaveGame saveGame3 = new SaveGame ( 3 , 2 , 100 , 1.5D );
        saveGame ( "D://Games//savegames//save_1.dat" , saveGame1 );
        saveGame ( "D://Games//savegames//save_2.dat" , saveGame2 );
        saveGame ( "D://Games//savegames//save_3.dat" , saveGame3 );
        List<String> list = new ArrayList<> ( );
        list.add ( "D://Games//savegames//save_1.dat" );
        list.add ( "D://Games//savegames//save_2.dat" );
        list.add ( "D://Games//savegames//save_3.dat" );
        zipFiles ( "D://Games//savegames//save.zip" , list );
    }

    public static void saveGame ( String path , SaveGame gameProgress ) {
        try (FileOutputStream fos = new FileOutputStream ( path );
             ObjectOutputStream oos = new ObjectOutputStream ( fos )) {
            oos.writeObject ( gameProgress );
        } catch (Exception ex) {
            System.out.println ( ex.getMessage ( ) );
        }
    }

    public static void zipFiles ( String path , List<String> fileList ) {
        try (ZipOutputStream zout = new ZipOutputStream ( new FileOutputStream ( path ) )) {
            for (String pth : fileList) {
                FileInputStream fis = new FileInputStream ( pth );
                ZipEntry entry = new ZipEntry ( pth.split ( "//" )[2] );
                zout.putNextEntry ( entry );
                byte[] buffer = new byte[fis.available ( )];
                fis.read ( buffer );
                zout.write ( buffer );
                fis.close ( );
                File file = new File ( pth );
                file.delete ( );
            }
        } catch (Exception e) {
            System.out.println ( e.toString ( ) );
        }
    }
}