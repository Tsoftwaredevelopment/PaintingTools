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
        super("ͼƬ");
        mainWindow=mainFrame;
        drawPanel=mainFrame.drawPanel;
        fileOption=fileMenu;
        addOperateItem();
        addPictureItem();
    }
    
    private void addOperateItem() {
        String []name= {"��","����","���ΪͼƬ"};
        ImageIcon []fileIcons=new ImageIcon[2];
        int []keyBoard= {KeyEvent.VK_F,KeyEvent.VK_G};
        for(int i=0;i<operateItem.length;i++) {
            if(i!=2) {
                fileIcons[i]=new ImageIcon("ImageIcons/MenuBarIcons/PictureIcons/"+name[i]+".png");
                operateItem[i]=new JMenuItem(name[i],fileIcons[i]);
                operateItem[i].setAccelerator(KeyStroke.getKeyStroke(keyBoard[i], InputEvent.CTRL_MASK));
                operateItem[i].addActionListener(this);
            }
            else//���ΪͼƬ��JMenu������MenuItem
                operateItem[i]=new JMenu(name[i]);
            add(operateItem[i]);
          }
    }
    
    private void addPictureItem() {
        String []name= {"PNGͼƬ","JPGͼƬ","GIFͼƬ"};
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
            else//�û���ѱ�����Ϊ���Ϊ  Ĭ�ϱ���ͼƬ��ʽΪ.png
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
                //���ﲻ��ֱ�Ӱ�saveWindowд��savePhoto()�Ĳ������֪��Ϊɶ��ֱ��д����ߣ���ʾ����ͼƬ��
                drawPanel.image=ImageIO.read(openPicture);
                fileOption.newFile();
            }catch(StreamCorruptedException e1) {
                JOptionPane.showMessageDialog(drawPanel, "���Ƕ������ֽ�������ļ�", "�ļ���ȡ������ʾ", JOptionPane.ERROR_MESSAGE);
            }catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private void savePicture0(String format) {
        savePicture=saveWindow();
        //������Ϊ��Ԥ���Ǹ��洢���ļ�ѡ������ʧ��̫����������ʱ����ļ�ѡ����Ҳ�ؽ�ȥ��
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
        int result=pictureChooser.showOpenDialog(drawPanel);//this Ϊ�����
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
          result=JOptionPane.showConfirmDialog(drawPanel, "���Ƿ�Ҫ���ǵ�ǰ�����ļ�?", "�����ļ���ʾ", JOptionPane.YES_NO_OPTION);
          if(result==JOptionPane.NO_OPTION)
            return saveWindow();
          else
            return src;//�������ǵ�ǰ�����ļ�
        }
        else
          return src;//��������ڱ���
      }

}
