package twicetry;

import java.io.Closeable;
import java.io.IOException;

/**
 * 工具类  用于关闭连接
 * @author 久拥-
 *
 */
public class OtherConnectionTo
{
    public static void close(Closeable ... connections)
    {
        for (Closeable connect : connections)
        {
            if(connect != null)
            {
                try
                {
                    connect.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
