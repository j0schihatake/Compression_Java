import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

import de.idyl.crypto.zip.AesZipFileEncrypter;

/**
 * This is a utility class having utility method for various kinds of
 * zip and unzip operation.
 * This class performs the following operations.
 * 1. Normal zipping a directory
 * 2. Zipping a directory with password
 * 3. Normal unzipping a zip file
 * 4. Unzipping a password protected zip file
 *
 * @author Debadatta Mishra(PIKU)
 *
 */
public final class ZipUtil
{
    /**This method is used to write the contents from a zip file to a file
     * @param file of type {@link File}
     * @param zipIn of type {@link ZipInputStream}
     */
    private static void writeFile( File file , ZipInputStream zipIn)
    {
        try
        {
            OutputStream outStream = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int len;
            while ((len = zipIn.read(buff)) > 0)
            {
                outStream.write(buff, 0, len);
            }
            outStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**This method is used to extract the zip file to a destination directory
     * @param srcZipfile of type {@link File} indicating the source zip file
     * @param destinationDir of type {@link File} indicating the destination
     * directory where the zip file will be extracted.
     * @throws IOException
     */
    private static void extract(File srcZipfile, File destinationDir) throws IOException
    {
        ZipInputStream zipIn = null;
        try
        {
            zipIn = new ZipInputStream(new FileInputStream(srcZipfile));
            ZipEntry entry = null;

            while ((entry = zipIn.getNextEntry()) != null)
            {
                String outFilename = entry.getName();

                if( !new File(destinationDir, outFilename).getParentFile().exists() )
                    new File(destinationDir, outFilename).getParentFile().mkdirs();

                if( !entry.isDirectory() )
                    writeFile(new File(destinationDir,outFilename), zipIn);
            }
            System.out.println("Zip file extracted successfully...");
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            if (zipIn != null)
            {
                zipIn.close();
            }
        }
    }

    /**This method is used to zip or compress a directory to create a zip file.
     * @param directory of type String indicating the source directory to be zipped
     * @param zos of type {@link ZipOutputStream}
     * @param path of type String indicating the path
     * @throws IOException
     */
    private static void compressDir(String directory, ZipOutputStream zos, String path) throws IOException {
        File zipDir = new File(directory);
        String[] dirList = zipDir.list();
        byte[] readBuffer = new byte[2156];
        int bytesIn = 0;
        for (int i = 0; i 0)
        {
            String path = destinationDir+File.separator+item.getPath().substring(0,item.getPath().lastIndexOf(File.separator));

            File folderExisting = new File(path);
            if (!folderExisting.exists())
                new File(path).mkdirs();
        }
        OutputStream out = new FileOutputStream(destinationDir+File.separator+item.getPath());
        out.write(data);
        out.close();
    }
catch( Exception e )
    {
        e.printStackTrace();
    }
    hash[0] |= Arrays.hashCode(data);
return data.length; // Return amount of proceed data
}
},password); /// password.
        if (result == ExtractOperationResult.OK)
        {
        System.out.println(String.format("%9X | %s",
        hash[0], item.getPath()));
        }
        else
        {
        System.err.println("Error extracting item: " + result);
        }
        }
        }
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
        finally
        {
        if (inArchive != null)
        {
        try
        {
        inArchive.close();
        }
        catch (SevenZipException e)
        {
        System.err.println("Error closing archive: " + e);
        e.printStackTrace();
        }
        }
        if (randomAccessFile != null)
        {
        try
        {
        randomAccessFile.close();
        }
        catch (IOException e)
        {
        System.err.println("Error closing file: " + e);
        e.printStackTrace();
        }
        }
        }
        }

        }