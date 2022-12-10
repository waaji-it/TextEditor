import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;


    TextEditor(){
        frame=new JFrame();

        textArea=new JTextArea();

        menuBar=new JMenuBar();


        file=new JMenu("File");
        edit=new JMenu("Edit");

        newFile=new JMenuItem("New");
        openFile=new JMenuItem("Open");
        saveFile=new JMenuItem("Save");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll =new JMenuItem("Select All");
        close=new JMenuItem("Close Window");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        frame.setBounds(100,100,800,500);
        frame.setVisible(true);

    }
    @Override
    public void actionPerfomed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow = new TextEditor();
        }
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try{
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    String intermediate="", output="";

                    while((intermediate=bufferedReader.readLine())!=null){
                        output=output+intermediate+"\n";
                    }
                    textArea.setText(output);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try{
                    BufferedWriter outfile=new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args){

        TextEditor textEditor=new TextEditor();

    }
}

