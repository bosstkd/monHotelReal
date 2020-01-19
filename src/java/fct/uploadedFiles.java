
package fct;

import controller.photoUploadController;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.UploadedFile;

public class uploadedFiles {
    
    
        public final  String IMAGE_TYPE_JPEG = "jpeg";
	public final  String IMAGE_TYPE_GIF = "gif";
	public final  String IMAGE_TYPE_PNG = "png";
    
    public  void logoReceiver(UploadedFile file, String code_h){
       
           InputStream InPtStream = null;
            OutputStream OtPtStream = null;
        try {
            InPtStream = file.getInputstream();
                       
            
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\");
            File f = new File(relativeWebPath); 
            new File(f.getPath()).mkdirs();
            new File(f.getPath()+"\\"+code_h).mkdirs();
            new File(f.getPath()+"\\"+code_h+"\\logo").mkdirs();
           
            OtPtStream = new FileOutputStream(new File(f.getPath()+"\\"+code_h+"\\logo\\logo.jpg"));

            int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = InPtStream.read(bytes)) != -1) {
			OtPtStream.write(bytes, 0, read);
		}
           
        } catch (IOException ex) {
            Logger.getLogger(photoUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
		if (InPtStream != null) {
			try {
				InPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (OtPtStream != null) {
			try {
				// outputStream.flush();
				OtPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
        File[] fs = new File[1] ;
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\"+code_h);
        File f = new File(relativeWebPath);
        fs[0] = new File(f.getPath()+"\\logo\\logo.jpg");
        logo(new Dimension(132,50),IMAGE_TYPE_JPEG,".jpg", fs); 
    }
    
    public  String photoReceiver(UploadedFile file, String code_h){
         InputStream InPtStream = null;
            OutputStream OtPtStream = null;
        try {
            
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\");
            File f = new File(relativeWebPath);
            
            InPtStream = file.getInputstream();
            new File(f.getPath()).mkdirs();
            new File(f.getPath()+"\\"+code_h).mkdirs();
            new File(f.getPath()+"\\"+code_h+"\\photos").mkdirs();
            OtPtStream = new FileOutputStream(new File(f.getPath()+"\\"+code_h+"\\photos\\"+file.getFileName()));
            
           int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = InPtStream.read(bytes)) != -1) {
			OtPtStream.write(bytes, 0, read);
		}
           
        } catch (IOException ex) {
            Logger.getLogger(photoUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
		if (InPtStream != null) {
			try {
				InPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (OtPtStream != null) {
			try {
				// outputStream.flush();
				OtPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
        File[] fs = new File[1] ;
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\"+code_h);
        File f = new File(relativeWebPath);
        
       
        fs[0] = new File(f.getPath()+"\\photos\\"+file.getFileName());
        logo(new Dimension(920,580),IMAGE_TYPE_JPEG,".jpg", fs);      
        return relativeWebPath+"\\photos\\";
    }
    
//-----------------------------------    
    
  public  String photoPubReceiver(UploadedFile file, String codePub){
         InputStream InPtStream = null;
            OutputStream OtPtStream = null;
        try {
            
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\publications\\");
            File f = new File(relativeWebPath);
            
            InPtStream = file.getInputstream();
            new File(f.getPath()).mkdirs();
            new File(f.getPath()+"\\"+codePub).mkdirs();
            OtPtStream = new FileOutputStream(new File(f.getPath()+"\\"+codePub+"\\"+file.getFileName()));
            
           int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = InPtStream.read(bytes)) != -1) {
			OtPtStream.write(bytes, 0, read);
		}
           
        } catch (IOException ex) {
            Logger.getLogger(photoUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
		if (InPtStream != null) {
			try {
				InPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (OtPtStream != null) {
			try {
				// outputStream.flush();
				OtPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
        File[] fs = new File[1] ;
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\publications\\"+codePub);
        File f = new File(relativeWebPath);
        
       
        fs[0] = new File(f.getPath()+"\\"+file.getFileName());
        logo(new Dimension(790,550),IMAGE_TYPE_JPEG,".jpg", fs);      
        return relativeWebPath+"\\";
    }
    
//-----------------------------------    
    
  public String getRelativePath(String toFind){
     return FacesContext.getCurrentInstance().getExternalContext().getRealPath(toFind);
  }  
    
  public List<String> getFileName(String relativeWebPath){
       File folder = new File(relativeWebPath);
        File[] listOfFiles = folder.listFiles();
        List<String> lstImageName = new ArrayList<String>();
         for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                     lstImageName.add(listOfFiles[i].getName());
                 }
            }
        return lstImageName;
  }  
  /*  
  public  void fileName(String relativeWebPath){
            File folder = new File(relativeWebPath);
            File[] listOfFiles = folder.listFiles();
             int j = 1;
             
            for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                File fl = new File(relativeWebPath+listOfFiles[i].getName());
                fl.renameTo(new File(relativeWebPath+j+".jpg"));
                j++;
                if( j > 4) j = 1;
            }
            }
            
            
            if(listOfFiles.length > 4){
                      
                 for (int i = 0; i < listOfFiles.length; i++) {                     
                    if (listOfFiles[i].isFile()) {
                        if(!(listOfFiles[i].getName().equals("1.jpg") || listOfFiles[i].getName().equals("2.jpg")|| listOfFiles[i].getName().equals("3.jpg") || listOfFiles[i].getName().equals("4.jpg") || listOfFiles[i].getName().equals("5.jpg") || listOfFiles[i].getName().equals("6.jpg"))){
                             
                               
                                Random n = new Random();
                                int rand = n.nextInt(4) + 1;
                              
                                File fl = new File(relativeWebPath+rand+".jpg");
                                fl.delete();
                                
                                fl = new File(relativeWebPath+listOfFiles[i].getName());
                                fl.renameTo(new File(relativeWebPath+rand+".jpg"));
                                
                        }
                    }
               }
            }
               
            
  }  
    
  */
   BufferedImage buf; 
	public  void logo(Dimension dms, String compressionType, String format, File[] fs) {
		for( int i = 0; i<fs.length; ++i){	
			File imgsup = new File(fs[i].getAbsolutePath().substring(0,fs[i].getAbsolutePath().length()-4)+"_RS"+format); 
		    imgsup.delete();
		    String pictureName = fs[i].getAbsolutePath().substring(0,fs[i].getAbsolutePath().length()-4)+"_RS"+format;
			try {
				buf = ImageIO.read(new File(fs[i].getAbsolutePath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    BufferedImage bufFinal = null; // Notre capture d'écran redimensionnée
		    // Création de la capture finale
		    bufFinal = new BufferedImage(dms.width,dms.height, BufferedImage.TYPE_INT_RGB);
		    // Redimensionnement de la capture originale
		    Graphics2D g = (Graphics2D) bufFinal.getGraphics();
		    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g.drawImage(buf, 0, 0, dms.width,dms.height, null);
		    g.dispose();
		    // Ecriture de notre capture d'écran redimensionnée
		    try {
		        ImageIO.write(bufFinal, compressionType, new File(pictureName));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
                    
                    imgsup = new File(fs[i].getAbsolutePath().substring(0,fs[i].getAbsolutePath().length()-4)+format); 
		    imgsup.delete();
                    
                    imgsup = new File(fs[i].getAbsolutePath().substring(0,fs[i].getAbsolutePath().length()-4)+".png"); 
		    imgsup.delete();
                   
                    
		 }
		buf = null;	
                
                
		}
        
        
public  void idPhotoReceiver(UploadedFile file, String npid){
       
           InputStream InPtStream = null;
            OutputStream OtPtStream = null;
        try {
            InPtStream = file.getInputstream();
                       
            
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\particulier\\");
            File f = new File(relativeWebPath); 
            new File(f.getPath()).mkdirs();
            new File(f.getPath()+"\\"+npid).mkdirs();
            new File(f.getPath()+"\\"+npid+"\\logo").mkdirs();
            OtPtStream = new FileOutputStream(new File(f.getPath()+"\\"+npid+"\\logo\\logo.jpg"));

            int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = InPtStream.read(bytes)) != -1) {
			OtPtStream.write(bytes, 0, read);
		}
           
        } catch (IOException ex) {
            Logger.getLogger(photoUploadController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
		if (InPtStream != null) {
			try {
				InPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (OtPtStream != null) {
			try {
				// outputStream.flush();
				OtPtStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
        File[] fs = new File[1] ;
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\particulier\\"+npid);
        File f = new File(relativeWebPath);
        fs[0] = new File(f.getPath()+"\\logo\\logo.jpg");
        logo(new Dimension(100,100),IMAGE_TYPE_JPEG,".jpg", fs); 
    }
        
        
        
        
        
}
