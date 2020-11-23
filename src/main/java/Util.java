import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {

    public static int buffersize = 1024;

    public static void extractArhiv(String source, String destination, String password) throws IOException {
        switch(FileNameUtils.getExtension(source)){
            case "zip":
                break;
            case "tar.gz":
                unTarGz(Paths.get(source), Paths.get(destination));
                break;
        }
    }



    // Метод для разархивирования targz
    public static void unTarGz( Path pathInput, Path pathOutput ) throws IOException {
        TarArchiveInputStream tararchiveinputstream =
                new TarArchiveInputStream(
                        new GzipCompressorInputStream(
                                new BufferedInputStream( Files.newInputStream( pathInput ) ) ) );

        ArchiveEntry archiveentry = null;
        while( (archiveentry = tararchiveinputstream.getNextEntry()) != null ) {
            Path pathEntryOutput = pathOutput.resolve( archiveentry.getName() );
            if( archiveentry.isDirectory() ) {
                if( !Files.exists( pathEntryOutput ) )
                    Files.createDirectory( pathEntryOutput );
            }
            else
                Files.copy( tararchiveinputstream, pathEntryOutput );
        }

        tararchiveinputstream.close();
    }
}
