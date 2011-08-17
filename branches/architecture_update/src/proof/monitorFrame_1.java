

package proof;

import java.awt.event.MouseListener;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import proof.FeedObjects.FeedObject;
import proof.Searcher.keywordInfo;
public class monitorFrame_1 extends javax.swing.JFrame {

final TrayIcon trayIcon =  new TrayIcon((new ImageIcon(getClass().getResource("/proof/tray.gif"), "tray icon")).getImage());


Map<String,FeedObjects.FeedObject> commentMap= new HashMap<String,FeedObjects.FeedObject>();
private Map<String,Map<String,FeedObjects.FeedObject>> resultsMap = new HashMap<String,Map<String,FeedObjects.FeedObject>>();
private DefaultListModel resultListModel = new DefaultListModel();
private DefaultListModel commentListModel = new DefaultListModel();
private DefaultComboBoxModel keywordsComboModel = new DefaultComboBoxModel();
private monitorEngine engine;
private monitorSettings monSettings;
Map<String,keywordInfo> keywordStats = new HashMap<String,keywordInfo>();
final SystemTray tray ;

 public monitorFrame_1(monitorSettings settings) {
        monSettings=settings;
        initComponents();
        if(SystemTray.isSupported()){
            
            tray=SystemTray.getSystemTray();
            initializeTray();
        }else{
            tray=null;
        }
            
        for(int j=0;j<settings.getKeywords().size();j++){
        resultsMap.put(settings.getKeywords().get(j).getKeyword(), new HashMap<String,FeedObjects.FeedObject>());
        }
        
        engine = new monitorEngine(settings);
        
        MonitorTimer timer = new MonitorTimer(settings.timerInterval);
        timer.start();
       
        this.updateMonitor(settings.timerInterval);       
    }




 public DefaultComboBoxModel initializeKeywordsCombo(){
    for(int i=0;i<monSettings.keys.size();i++){
        keywordsComboModel.addElement(monSettings.keys.get(i).getKeyword());
    }

     return keywordsComboModel;
 }




    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commentsFrame = new javax.swing.JFrame();
        jScrollPane8 = new javax.swing.JScrollPane();
        commentList = new javax.swing.JList();
        comFromLabel = new javax.swing.JLabel();
        comFromField = new javax.swing.JTextField();
        comCreatedLabel = new javax.swing.JLabel();
        comCreatedField = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        comMessageArea = new javax.swing.JTextArea();
        comMessageLabel = new javax.swing.JLabel();
        trayPopup = new java.awt.PopupMenu();
        Exit = new java.awt.MenuItem();
        Show = new java.awt.MenuItem();
        monitorTabedPane = new javax.swing.JTabbedPane();
        fbPanel = new javax.swing.JPanel();
        sourceField = new javax.swing.JTextField();
        sourceLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        nameField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        showCommentsButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        foundList = new javax.swing.JList();
        fromLabel = new javax.swing.JLabel();
        fromField = new javax.swing.JTextField();
        resultsLabel = new javax.swing.JLabel();
        typeField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        createdField = new javax.swing.JTextField();
        captionLabel = new javax.swing.JLabel();
        linkLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        captionField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        foundAtLabel = new javax.swing.JLabel();
        foundAtField = new javax.swing.JTextField();
        createdLabel = new javax.swing.JLabel();
        linkField = new javax.swing.JTextField();
        keywordsCombo = new javax.swing.JComboBox();
        lastResultsFoundField = new javax.swing.JTextField();
        totalResultsFound = new javax.swing.JTextField();
        averageResultsFound = new javax.swing.JTextField();
        groupedLabel = new javax.swing.JLabel();
        lastSearchLabel = new javax.swing.JLabel();
        nextSearchLabel = new javax.swing.JLabel();

        commentsFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        commentList.setModel(commentListModel);
        commentList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                commentListKeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(commentList);

        comFromLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        comFromLabel.setText("From");

        comFromField.setEditable(false);
        comFromField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comFromFieldActionPerformed(evt);
            }
        });

        comCreatedLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        comCreatedLabel.setText("Created");

        comCreatedField.setEditable(false);

        comMessageArea.setColumns(20);
        comMessageArea.setLineWrap(true);
        comMessageArea.setRows(5);
        jScrollPane9.setViewportView(comMessageArea);

        comMessageLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        comMessageLabel.setText("Message");

        javax.swing.GroupLayout commentsFrameLayout = new javax.swing.GroupLayout(commentsFrame.getContentPane());
        commentsFrame.getContentPane().setLayout(commentsFrameLayout);
        commentsFrameLayout.setHorizontalGroup(
            commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commentsFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(commentsFrameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(commentsFrameLayout.createSequentialGroup()
                                .addComponent(comCreatedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comCreatedField))
                            .addGroup(commentsFrameLayout.createSequentialGroup()
                                .addComponent(comFromLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comFromField, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9)))
                    .addGroup(commentsFrameLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(comMessageLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        commentsFrameLayout.setVerticalGroup(
            commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commentsFrameLayout.createSequentialGroup()
                .addGroup(commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(commentsFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(commentsFrameLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comFromLabel)
                            .addComponent(comFromField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(commentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comCreatedLabel)
                            .addComponent(comCreatedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(comMessageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );

        trayPopup.setLabel("popupMenu1");
        trayPopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trayPopupActionPerformed(evt);
            }
        });

        Exit.setLabel("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        trayPopup.add(Exit);

        Show.setLabel("Show");
        Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowActionPerformed(evt);
            }
        });
        trayPopup.add(Show);

        setTitle("Vdella Monitor");
        setIconImage((new ImageIcon(getClass().getResource("/proof/tray.gif"), "tray icon")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        fbPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sourceField.setEditable(false);
        fbPanel.add(sourceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 490, -1));

        sourceLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        sourceLabel.setText("Source");
        fbPanel.add(sourceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        messageArea.setColumns(20);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setRows(5);
        jScrollPane6.setViewportView(messageArea);

        fbPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 610, 70));

        nameField.setEditable(false);
        fbPanel.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 600, -1));

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        nameLabel.setText("Name");
        fbPanel.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, -1, -1));

        messageLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        messageLabel.setText("Message");
        fbPanel.add(messageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, -1));
        fbPanel.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 110));

        showCommentsButton.setText("Show Comments");
        showCommentsButton.setEnabled(false);
        showCommentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCommentsButtonActionPerformed(evt);
            }
        });
        fbPanel.add(showCommentsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 160, -1));

        foundList.setModel(resultListModel);
        foundList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        foundList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                foundListKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                foundListKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(foundList);

        fbPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 160, 241));

        fromLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        fromLabel.setText("From");
        fbPanel.add(fromLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 39, -1));

        fromField.setEditable(false);
        fbPanel.add(fromField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 126, -1));

        resultsLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        resultsLabel.setText("Results");
        fbPanel.add(resultsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 75, 26));

        typeField.setEditable(false);
        fbPanel.add(typeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 80, -1));

        typeLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        typeLabel.setText("Type");
        fbPanel.add(typeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 39, -1));

        createdField.setEditable(false);
        fbPanel.add(createdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 190, -1));

        captionLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        captionLabel.setText("Caption");
        fbPanel.add(captionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 60, -1));

        linkLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        linkLabel.setText("Link");
        fbPanel.add(linkLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 37, 28));

        descriptionArea.setColumns(20);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setRows(5);
        jScrollPane5.setViewportView(descriptionArea);

        fbPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 610, 50));

        captionField.setEditable(false);
        fbPanel.add(captionField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 100, -1));

        descriptionLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        descriptionLabel.setText("Description");
        fbPanel.add(descriptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 120, -1));

        foundAtLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        foundAtLabel.setText("Found at");
        fbPanel.add(foundAtLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, -1));

        foundAtField.setEditable(false);
        fbPanel.add(foundAtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 170, -1));

        createdLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        createdLabel.setText("Created Time");
        fbPanel.add(createdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        linkField.setEditable(false);
        linkField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fbPanel.add(linkField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 610, -1));

        keywordsCombo.setModel(initializeKeywordsCombo());
        keywordsCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keywordsComboActionPerformed(evt);
            }
        });
        keywordsCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                keywordsComboPropertyChange(evt);
            }
        });
        fbPanel.add(keywordsCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, -1));

        lastResultsFoundField.setEditable(false);
        lastResultsFoundField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastResultsFoundFieldActionPerformed(evt);
            }
        });
        fbPanel.add(lastResultsFoundField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 190, -1));

        totalResultsFound.setEditable(false);
        fbPanel.add(totalResultsFound, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 140, -1));

        averageResultsFound.setEditable(false);
        fbPanel.add(averageResultsFound, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 240, -1));

        groupedLabel.setText("Results grouped by keyword");
        fbPanel.add(groupedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, -1));

        monitorTabedPane.addTab("Results", fbPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lastSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(nextSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(monitorTabedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(monitorTabedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lastSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        public  void initializeTray(){
          try{
        tray.add(trayIcon);
        trayIcon.setPopupMenu(trayPopup);
        trayIcon.setImageAutoSize(true);
        }catch(Exception e){}
        MouseListener mouseListener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()==2){
            setVisible(true);
             }
        }
        public void mouseEntered(MouseEvent e) {

        }
        public void mouseExited(MouseEvent e) {

        }
        public void mousePressed(MouseEvent e) {

        }
        public void mouseReleased(MouseEvent e) {

        }
    };
    trayIcon.addMouseListener(mouseListener);

    }


    private void showCommentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCommentsButtonActionPerformed

        FeedObjects.FeedObject viewedObject,commentObject;
        viewedObject=resultsMap.get(keywordsCombo.getSelectedItem().toString()).get(foundList.getSelectedValue().toString());

        commentMap=viewedObject.getComments();

        if(commentMap==null){
            System.out.println("No comments");
        } else{
            commentListModel.clear();
            commentsFrame.setVisible(true);
            commentsFrame.setBounds(this.getX()+this.getWidth(),this.getY(), 500, 300);
            comFromField.setText("");
            comCreatedField.setText("");
            comMessageArea.setText("");

            commentMap = UtilityFunctions.mapSort.sortByValue(commentMap);
            Iterator commentIterator =  commentMap.entrySet().iterator();
            Map.Entry commentEntry;

            while(commentIterator.hasNext()){

                commentEntry = (Map.Entry) commentIterator.next();
                commentObject=(FeedObject) commentEntry.getValue();
                commentListModel.addElement(commentObject.getId());
            }

        }

    }//GEN-LAST:event_showCommentsButtonActionPerformed

    private void foundListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foundListKeyPressed
    }//GEN-LAST:event_foundListKeyPressed

    private void commentListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commentListKeyPressed
        FeedObjects.FeedObject viewedComment;
        viewedComment=commentMap.get(commentListModel.get(commentList.getSelectedIndex()).toString());
        comFromField.setText(viewedComment.getFromName());
        comCreatedField.setText(viewedComment.getCreatedTime().toString());
        comMessageArea.setText(viewedComment.getMessage());
}//GEN-LAST:event_commentListKeyPressed



      public void refreshResultList(String keyword){
          Searcher.keywordInfo info;
          resultListModel.clear();

        Iterator mapIter = resultsMap.get(keyword).entrySet().iterator();
        info=keywordStats.get(keyword);

        lastResultsFoundField.setText(String.valueOf(info.getLastTimeFound())+": Results Found last time");
        totalResultsFound.setText(String.valueOf(info.getTotalTimesFound())+": Times found total");
        averageResultsFound.setText(String.valueOf(info.getAverageTimesFound())+": Average times found per search");

        for(int i=0;i<resultsMap.get(keyword).size();i++){
         Map.Entry entry = (Map.Entry) mapIter.next();
         resultListModel.addElement(entry.getKey().toString());
        }



      }
      public void updateResultView(){

        try{
         FeedObjects.FeedObject viewedObject;
         viewedObject=resultsMap.get(keywordsCombo.getSelectedItem().toString()).get(foundList.getSelectedValue().toString());
         fromField.setText(viewedObject.getFromName());
         typeField.setText(viewedObject.getType());
         captionField.setText(viewedObject.getCaption());
         linkField.setText(viewedObject.getLink());
         sourceField.setText(viewedObject.getSource());
         descriptionArea.setText(viewedObject.getDescription());
         messageArea.setText(viewedObject.getMessage());
         nameField.setText(viewedObject.getName());
         createdField.setText(viewedObject.getCreatedTime().toString());
         foundAtField.setText(viewedObject.getSiteSource());
         try{
            java.net.URL where = new URL(viewedObject.getPicture());
            ImageIcon anotherIcon = new ImageIcon(where);
            imageLabel.setIcon( anotherIcon);

        }catch(Exception e){
        try{
        ImageIcon icon = new ImageIcon(getClass().getResource("/proof/nophoto.gif"));
             imageLabel.setIcon(icon);}catch(Exception e2){}
        }
        if(viewedObject.getComments()!=null){
            showCommentsButton.setEnabled(true);
        }
        else{
             showCommentsButton.setEnabled(false);
        }
        }catch(Exception e){ }
      }
    private void comFromFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comFromFieldActionPerformed

}//GEN-LAST:event_comFromFieldActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        tray.remove(trayIcon);
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void trayPopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trayPopupActionPerformed
    
}//GEN-LAST:event_trayPopupActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
              trayIcon.displayMessage("Application minimized", "The application is still running hiden \n" +
                 "Right click and press exit if you want to terminate", TrayIcon.MessageType.NONE);
    }//GEN-LAST:event_formWindowClosing

    private void ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowActionPerformed
        this.setVisible(true);
    }//GEN-LAST:event_ShowActionPerformed

    private void keywordsComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_keywordsComboPropertyChange

}//GEN-LAST:event_keywordsComboPropertyChange

    private void keywordsComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keywordsComboActionPerformed
        this.refreshResultList(keywordsCombo.getSelectedItem().toString());
}//GEN-LAST:event_keywordsComboActionPerformed

    private void foundListKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_foundListKeyTyped
        if(evt.getKeyChar()=='\n'){
            this.updateResultView();
        }
    }//GEN-LAST:event_foundListKeyTyped

    private void lastResultsFoundFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastResultsFoundFieldActionPerformed

    }//GEN-LAST:event_lastResultsFoundFieldActionPerformed
 
    public class MonitorTimer implements ActionListener{
        private Timer t;
        int Interval;
        MonitorTimer(int interval){
            t=new Timer(interval,this);
            Interval=interval;
        }

        public void start(){
            t.start();
        }
        public void stop(){
            t.stop();
        }

     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t) {
                    updateMonitor(Interval);
        }
    }
    }
     public void updateMonitor(int interval){
          

            Date searchTime = timeHandler.getCurrentTime().getTime();
            long next;
            Map<String,Map<String,FeedObjects.FeedObject>> recentResults= new HashMap<String,Map<String,FeedObjects.FeedObject>>();
       


            recentResults=engine.performSearch();
            keywordStats=engine.getKeywordStats();
            trayNotifier();

            for(int i=0;i<monSettings.keys.size();i++){
            
            resultsMap.get(monSettings.getKeywords().get(i).getKeyword()).putAll(recentResults.get(monSettings.getKeywords().get(i).getKeyword()));
            resultsMap.put(monSettings.getKeywords().get(i).getKeyword(),UtilityFunctions.mapSort.sortByValue(resultsMap.get(monSettings.getKeywords().get(i).getKeyword())));
            }


                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                lastSearchLabel.setText("Last Search Performed At : "+sdf.format(searchTime));
                next=searchTime.getTime()+interval;
                searchTime.setTime(next);
                nextSearchLabel.setText("Next Search Will Be Performed At : "+sdf.format(searchTime));
                trayIcon.setToolTip("Next Search Will Be Performed At : "+sdf.format(searchTime));
                if(keywordsCombo.getSelectedItem()!=null)
                {refreshResultList(keywordsCombo.getSelectedItem().toString());}
       

     }

     private void trayNotifier(){
        String notification=engine.getNotifications();
        if(notification!=null){
        trayIcon.displayMessage("Notification event ocured", notification, TrayIcon.MessageType.INFO);
        }
     }
     

   public static void main(final monitorSettings settings) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new monitorFrame(settings).setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.MenuItem Exit;
    private java.awt.MenuItem Show;
    private javax.swing.JTextField averageResultsFound;
    private javax.swing.JTextField captionField;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JTextField comCreatedField;
    private javax.swing.JLabel comCreatedLabel;
    private javax.swing.JTextField comFromField;
    private javax.swing.JLabel comFromLabel;
    private javax.swing.JTextArea comMessageArea;
    private javax.swing.JLabel comMessageLabel;
    private javax.swing.JList commentList;
    private javax.swing.JFrame commentsFrame;
    private javax.swing.JTextField createdField;
    private javax.swing.JLabel createdLabel;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JPanel fbPanel;
    private javax.swing.JTextField foundAtField;
    private javax.swing.JLabel foundAtLabel;
    private javax.swing.JList foundList;
    private javax.swing.JTextField fromField;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel groupedLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JComboBox keywordsCombo;
    private javax.swing.JTextField lastResultsFoundField;
    private javax.swing.JLabel lastSearchLabel;
    private javax.swing.JTextField linkField;
    private javax.swing.JLabel linkLabel;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTabbedPane monitorTabedPane;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nextSearchLabel;
    private javax.swing.JLabel resultsLabel;
    private javax.swing.JButton showCommentsButton;
    private javax.swing.JTextField sourceField;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JTextField totalResultsFound;
    private java.awt.PopupMenu trayPopup;
    private javax.swing.JTextField typeField;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables

}
