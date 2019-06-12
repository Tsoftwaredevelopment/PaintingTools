package menuoption;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import mainframecommponent.DrawBoard;
import workstation.MainFrame;

public class PictureMenu extends JMenu implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuItem []operateItem=new JMenuItem[3];
    private JMenuItem []pictureItem=new JMenuItem[3];
    private MainFrame mainWindow;
    private DrawBoard drawPanel;
    private FileMenu fileOption;
    private BufferedImage image;
    private File openPicture=null,savePicture;
    private String openPictureName;

    public PictureMenu(MainFrame mainFrame,FileMenu fileMenu) {
        super("图片");
        mainWindow=mainFrame;
        drawPanel=mainFrame.drawPanel;
        fileOption=fileMenu;
        addOperateItem();
        addPictureItem();
    }
    
    private void addOperateItem() {
        String []name= {"打开","保存","另存为图片"};
        ImageIcon []fileIcons=new ImageIcon[2];
        int []keyBoard= {KeyEvent.VK_F,KeyEvent.VK_G};
        for(int i=0;i<operateItem.length;i++) {
            if(i!=2) {
                fileIcons[i]=new ImageIcon("ImageIcons/MenuBarIcons/PictureIcons/"+name[i]+".png");
                operateItem[i]=new JMenuItem(name[i],fileIcons[i]);
                operateItem[i].setAccelerator(KeyStroke.getKeyStroke(keyBoard[i], InputEvent.CTRL_MASK));
                operateItem[i].addActionListener(this);
            }
            else//另存为图片是JMenu还有子MenuItem
                operateItem[i]=new JMenu(name[i]);
            add(operateItem[i]);
          }
    }
    
    private void addPictureItem() {
        String []name= {"PNG图片","JPG图片","GIF图片"};
        ImageIcon []fileIcons=new ImageIcon[3];
        for(int i=0;i<pictureItem.length;i++) {
            fileIcons[i]=new ImageIcon("ImageIcons/MenuBarIcons/PictureIcons/"+name[i]+".png");
            pictureItem[i]=new JMenuItem(name[i],fileIcons[i]);
            pictureItem[i].addActionListener(this);
            operateItem[2].add(pictureItem[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==operateItem[0]) {
            openPhoto();
        }
        else if(e.getSource()==operateItem[1]) {
            if(openPicture!=null) {
                savePicture=openPicture;
                openPictureName=savePicture.getName();
                savePhoto(savePicture,openPictureName.substring(openPictureName.lastIndexOf(".")+1));                
            }
            else//用户错把保存作为另存为  默认保存图片格式为.png
                savePicture0("png");
        }
        else if(e.getSource()==pictureItem[0])
            savePicture0("png");
        else if(e.getSource()==pictureItem[1])
            savePicture0("jpg");
        else if(e.getSource()==pictureItem[2])
            savePicture0("gif");
            
    }
    
    private void openPhoto() {
        openPicture=openWindow();
        if(openPicture!=null) {
            try {
                //这里不能直接把saveWindow写在savePhoto()的参数里，不知道为啥，直接写在里边，显示不了图片。
                drawPanel.image=ImageIO.read(openPicture);
                fileOption.newFile();
            }catch(StreamCorruptedException e1) {
                JOptionPane.showMessageDialog(drawPanel, "并非二进制字节码对象文件", "文件读取错误提示", JOptionPane.ERROR_MESSAGE);
            }catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private void savePicture0(String format) {
        savePicture=saveWindow();
        //这里是为了预防那个存储的文件选择器消失地太慢，截屏的时候把文件选择器也截进去了
        try {
            Thread.sleep(300);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        savePhoto(savePicture,format);
    }
    
    private void savePhoto(File src,String format) {
        if(src!=null) {
            try {
                image=new Robot().createScreenCapture(new Rectangle(mainWindow.getX()+44, mainWindow.getY()+193, drawPanel.getWidth(), drawPanel.getHeight()));
                ImageIO.write(image, format, src);
                openPicture=null;
            } catch (AWTException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private File openWindow() {
        JFileChooser pictureChooser=new JFileChooser();
        pictureChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=pictureChooser.showOpenDialog(drawPanel);//this 为父组件
        if(result==JFileChooser.CANCEL_OPTION) return null;
        return pictureChooser.getSelectedFile();
      }
    
    private  File saveWindow() {
        JFileChooser pictureChooser=new JFileChooser();
        pictureChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=pictureChooser.showSaveDialog(drawPanel);
        if(result==JFileChooser.CANCEL_OPTION) return null;
        File src=pictureChooser.getSelectedFile();
        if(src.exists()) {
          result=JOptionPane.showConfirmDialog(drawPanel, "您是否要覆盖当前本地文件?", "保存文件提示", JOptionPane.YES_NO_OPTION);
          if(result==JOptionPane.NO_OPTION)
            return saveWindow();
          else
            return src;//表明覆盖当前本地文件
        }
        else
          return src;//表明另存在本地
      }

}
