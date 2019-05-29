package Imagens;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import javafx.scene.image.Image;

/**
 *
 * @author anadi
 */
public class ConverterImagemByte {
    
    public static Image paraImagem(byte[] bytes) 
            throws ClassNotFoundException, IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); 
                ObjectInput in = new ObjectInputStream(bis)) {
            ImagemSerializavel image = (ImagemSerializavel) in.readObject();
            
            return image.getImage();
        }
    }

    public static byte[] paraByteArray(Image imagem) throws IOException {
        ImagemSerializavel image = new ImagemSerializavel(imagem);
        
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(image);
            out.flush();
            return bos.toByteArray();
        }
    }
            
    
}
