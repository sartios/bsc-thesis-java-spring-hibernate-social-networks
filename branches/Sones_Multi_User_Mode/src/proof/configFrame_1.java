

package proof;



import javax.swing.DefaultListModel;

import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.User;



public class configFrame_1 extends javax.swing.JFrame {
    private DefaultListModel pSitesListModel = new DefaultListModel();
    private DefaultListModel uSitesListModel = new DefaultListModel();
    private DefaultListModel frSitesListModel = new DefaultListModel();
    private DefaultListModel keywordListModel = new DefaultListModel();
    private DefaultListModel twitterListModel = new DefaultListModel();
    private DefaultListModel twitterFriendsListModel = new DefaultListModel();
    private DefaultListModel twitterFollowersListModel = new DefaultListModel();
    private DefaultListModel keysComboListModel = new DefaultListModel();
    private DefaultComboBoxModel keysComboComboModel = new DefaultComboBoxModel();
    FeedObjects.entityObject[] uSitelist,frList;
    List<FeedObjects.keywordObject> keywordObjList = new ArrayList();
    List<FeedObjects.comboObject> comboObjList = new ArrayList();
    configFrameSettings settings;
    Twitter twitter;
    


    public configFrame_1(configFrameSettings set) {
        settings=set;

          if(settings.twAuth==true){
          twitter = set.twitter;
        }
        initComponents();
        modeRadioGroup.add(this.orModeRadio);
        modeRadioGroup.add(this.andModeRadio);
        modeRadioGroup.setSelected(andModeRadio.getModel(),true);
        firstSearchSpinner.setValue(timeHandler.getCurrentTime().getTime());
      

        
    }

 






    public DefaultListModel initializePSiteLists(){
      
        try{    
        List<String> siteList;

        siteList=FileHandler.getListFromFile("psiteslist.txt");
        
        for(int i=0;i<siteList.size();i++){
        pSitesListModel.add(i, siteList.get(i));
        }
        }catch(Exception GuiException){System.out.println(GuiException.getMessage());}
             
        return pSitesListModel;
    }
        public DefaultListModel initializeTwitterList(){

        try{
        List<String> siteList;

        siteList=FileHandler.getListFromFile("twitterlist.txt");

        for(int i=0;i<siteList.size();i++){
        twitterListModel.add(i, siteList.get(i));
        }
        }catch(Exception GuiException){System.out.println(GuiException.getMessage());}

        return twitterListModel;
    }
    public DefaultListModel initializeUSiteLists(){
      
        if(settings.fbAuth==true){
        try{
       
        uSitelist=JsonHandler.ExtractEntitysFromRest(RestHandler.getPagesUserLikes(settings.fbToken));
        
        for(int i=0;i<uSitelist.length;i++){
           
        uSitesListModel.add(i, uSitelist[i].getName());
        }
      }catch(Exception GuiException)
      {System.out.println(GuiException.getMessage());}
        
        }
        return uSitesListModel;
    }
    public DefaultListModel initializeFrSiteLists(){
       if(settings.fbAuth==true){
        try{
        
       
        frList=JsonHandler.ExtractEntitysFromRest(RestHandler.getFriends(settings.fbToken));
       
        for(int i=0;i<frList.length;i++){
        frSitesListModel.add(i, frList[i].getName());
        }
      }catch(Exception GuiException)
      {System.out.println(GuiException.getMessage());}
        
       }
        return frSitesListModel;
    }
    public DefaultListModel initializeTwitterFriendsList(){
    
        if(settings.twAuth==true){
            try {
               PagableResponseList<User> response;
                response = twitter.getFriendsStatuses();
                for(int i=0;i<response.size();i++){
                twitterFriendsListModel.addElement(response.get(i).getScreenName());
                }

            } catch(Exception ex){System.out.println(ex.getMessage());}
        }

        return twitterFriendsListModel;
    }
    public DefaultListModel initializeTwitterFollowersList(){

        if(settings.twAuth==true){
            try {
               PagableResponseList<User> response;
                response = twitter.getFollowersStatuses();
                for(int i=0;i<response.size();i++){
                twitterFollowersListModel.addElement(response.get(i).getScreenName());
                }

            } catch(Exception ex){System.out.println(ex.getMessage());}
        }

        return twitterFollowersListModel;
    }





    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modeRadioGroup = new javax.swing.ButtonGroup();
        configPane = new javax.swing.JTabbedPane();
        twSourcesPanel = new javax.swing.JPanel();
        twitterListLabel = new javax.swing.JLabel();
        twitterSitesAll = new javax.swing.JButton();
        twitterSitesNone = new javax.swing.JButton();
        addTwitterUser = new javax.swing.JButton();
        removeTwitterUser = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        twitterPublicList = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        twitterFriendList = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        twitterFollowersList = new javax.swing.JList();
        friendsLabel = new javax.swing.JLabel();
        followersLabel = new javax.swing.JLabel();
        friendsAllButton = new javax.swing.JButton();
        friendsNoneButton = new javax.swing.JButton();
        followersAllButton = new javax.swing.JButton();
        followersNoneButton = new javax.swing.JButton();
        fbSourcesPanel = new javax.swing.JPanel();
        pSitesAll = new javax.swing.JButton();
        pSitesNone = new javax.swing.JButton();
        uSitesNone = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pSitesList = new javax.swing.JList();
        uSitesAll = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        uSitesList = new javax.swing.JList();
        frSitesAll = new javax.swing.JButton();
        frSitesNone = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        frSitesList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        intervalLabel = new javax.swing.JLabel();
        intervalSpinner = new javax.swing.JSpinner();
        minutesLabel = new javax.swing.JLabel();
        addSiteButton = new javax.swing.JButton();
        removeSiteButton = new javax.swing.JButton();
        commentsCheckBox = new javax.swing.JCheckBox();
        startButton = new javax.swing.JButton();
        notifyEveryTimeBox = new javax.swing.JCheckBox();
        averagePMNotifyBox = new javax.swing.JCheckBox();
        singleExeedBox = new javax.swing.JCheckBox();
        perMinuteSpinner = new javax.swing.JSpinner();
        overalSpinner = new javax.swing.JSpinner();
        overalCheckBox = new javax.swing.JCheckBox();
        pmPercentSpinner = new javax.swing.JSpinner();
        pmPercentLabel = new javax.swing.JLabel();
        pmMinutesLabel = new javax.swing.JLabel();
        lifetimeNotifyBox = new javax.swing.JCheckBox();
        lifetimeSpinner = new javax.swing.JSpinner();
        lifetimeLabel = new javax.swing.JLabel();
        keywordsPane = new javax.swing.JTabbedPane();
        keywordsPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        keywordList = new javax.swing.JList();
        addWordButton = new javax.swing.JButton();
        removeWordButton = new javax.swing.JButton();
        keywordsLabel = new javax.swing.JLabel();
        combosPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        comboKeyList = new javax.swing.JList();
        keyComboComboBox = new javax.swing.JComboBox();
        newComboButton = new javax.swing.JButton();
        andModeRadio = new javax.swing.JRadioButton();
        orModeRadio = new javax.swing.JRadioButton();
        comboNameField = new javax.swing.JTextField();
        addToComboButton = new javax.swing.JButton();
        removeFromComboButton = new javax.swing.JButton();
        comboKeywordsLabel = new javax.swing.JLabel();
        comboNameLabel = new javax.swing.JLabel();
        createdCombosLabel = new javax.swing.JLabel();
        removeComboButton = new javax.swing.JButton();
        thresholdLabel = new javax.swing.JLabel();
        thresholdSpinner = new javax.swing.JSpinner();
        keywordField = new javax.swing.JTextField();
        firstSearchSpinner = new javax.swing.JSpinner();
        firstSearchBox = new javax.swing.JCheckBox();
        fbSearchNowBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vdella Configuration");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage((new ImageIcon(getClass().getResource("/proof/tray.gif"), "tray icon")).getImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        configPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        twSourcesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        twSourcesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        twitterListLabel.setText("Public Users");
        twSourcesPanel.add(twitterListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 160, -1));

        twitterSitesAll.setFont(new java.awt.Font("Tahoma", 0, 9));
        twitterSitesAll.setText("All");
        twitterSitesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twitterSitesAllActionPerformed(evt);
            }
        });
        twSourcesPanel.add(twitterSitesAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, 23));

        twitterSitesNone.setFont(new java.awt.Font("Tahoma", 0, 9));
        twitterSitesNone.setText("None");
        twitterSitesNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twitterSitesNoneActionPerformed(evt);
            }
        });
        twSourcesPanel.add(twitterSitesNone, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 70, 23));

        addTwitterUser.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        addTwitterUser.setText("Add");
        addTwitterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTwitterUserActionPerformed(evt);
            }
        });
        twSourcesPanel.add(addTwitterUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 70, 23));

        removeTwitterUser.setFont(new java.awt.Font("Tahoma", 0, 9));
        removeTwitterUser.setText("Remove");
        removeTwitterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTwitterUserActionPerformed(evt);
            }
        });
        twSourcesPanel.add(removeTwitterUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 70, 23));

        twitterPublicList.setModel(initializeTwitterList());
        jScrollPane4.setViewportView(twitterPublicList);

        twSourcesPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, 150));

        twitterFriendList.setModel(initializeTwitterFriendsList());
        jScrollPane5.setViewportView(twitterFriendList);

        twSourcesPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 220, 150));

        twitterFollowersList.setModel(initializeTwitterFollowersList());
        jScrollPane6.setViewportView(twitterFollowersList);

        twSourcesPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 220, 150));

        friendsLabel.setText("Following");
        twSourcesPanel.add(friendsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 100, -1));

        followersLabel.setText("Followers");
        twSourcesPanel.add(followersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 100, -1));

        friendsAllButton.setFont(new java.awt.Font("Tahoma", 0, 9));
        friendsAllButton.setText("All");
        friendsAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendsAllButtonActionPerformed(evt);
            }
        });
        twSourcesPanel.add(friendsAllButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 70, 23));

        friendsNoneButton.setFont(new java.awt.Font("Tahoma", 0, 9));
        friendsNoneButton.setText("None");
        friendsNoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendsNoneButtonActionPerformed(evt);
            }
        });
        twSourcesPanel.add(friendsNoneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 70, 23));

        followersAllButton.setFont(new java.awt.Font("Tahoma", 0, 9));
        followersAllButton.setText("All");
        followersAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followersAllButtonActionPerformed(evt);
            }
        });
        twSourcesPanel.add(followersAllButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 70, 23));

        followersNoneButton.setFont(new java.awt.Font("Tahoma", 0, 9));
        followersNoneButton.setText("None");
        followersNoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followersNoneButtonActionPerformed(evt);
            }
        });
        twSourcesPanel.add(followersNoneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 70, 23));

        configPane.addTab("Twitter Sources", twSourcesPanel);

        fbSourcesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pSitesAll.setFont(new java.awt.Font("Tahoma", 0, 10));
        pSitesAll.setText("All");
        pSitesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pSitesAllActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(pSitesAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 70, 23));
        pSitesAll.getAccessibleContext().setAccessibleName("");

        pSitesNone.setFont(new java.awt.Font("Tahoma", 0, 10));
        pSitesNone.setText("None");
        pSitesNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pSitesNoneActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(pSitesNone, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 70, 23));

        uSitesNone.setFont(new java.awt.Font("Tahoma", 0, 10));
        uSitesNone.setText("None");
        uSitesNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uSitesNoneActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(uSitesNone, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 70, 23));

        pSitesList.setModel(initializePSiteLists());
        pSitesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(pSitesList);

        fbSourcesPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 220, 150));

        uSitesAll.setFont(new java.awt.Font("Tahoma", 0, 10));
        uSitesAll.setText("All");
        uSitesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uSitesAllActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(uSitesAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 70, 23));
        uSitesAll.getAccessibleContext().setAccessibleName("");

        uSitesList.setModel(initializeUSiteLists());
        jScrollPane2.setViewportView(uSitesList);

        fbSourcesPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 220, 150));

        frSitesAll.setFont(new java.awt.Font("Tahoma", 0, 10));
        frSitesAll.setText("All");
        frSitesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frSitesAllActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(frSitesAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 70, 23));
        frSitesAll.getAccessibleContext().setAccessibleName("");

        frSitesNone.setFont(new java.awt.Font("Tahoma", 0, 10));
        frSitesNone.setText("None");
        frSitesNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frSitesNoneActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(frSitesNone, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 70, 23));

        frSitesList.setModel(initializeFrSiteLists());
        jScrollPane3.setViewportView(frSitesList);

        fbSourcesPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 220, 150));

        jLabel1.setText("Public Sites");
        fbSourcesPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 80, 22));

        jLabel2.setText("Acount Sites");
        fbSourcesPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 113, 18));

        jLabel3.setText("Friends");
        fbSourcesPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 113, 18));

        intervalLabel.setText("Search Every");
        fbSourcesPanel.add(intervalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 98, 19));

        intervalSpinner.setModel(new javax.swing.SpinnerNumberModel(5, 5, 120, 1));
        fbSourcesPanel.add(intervalSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 60, -1));

        minutesLabel.setText("Minutes");
        fbSourcesPanel.add(minutesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 50, -1));

        addSiteButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        addSiteButton.setText("Add");
        addSiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSiteButtonActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(addSiteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 70, 23));
        addSiteButton.getAccessibleContext().setAccessibleName("");

        removeSiteButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        removeSiteButton.setText("Remove");
        removeSiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSiteButtonActionPerformed(evt);
            }
        });
        fbSourcesPanel.add(removeSiteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 70, 23));

        commentsCheckBox.setText("Search Comments");
        fbSourcesPanel.add(commentsCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 163, -1));

        configPane.addTab("Facebook Sources", fbSourcesPanel);
        fbSourcesPanel.getAccessibleContext().setAccessibleName("");

        getContentPane().add(configPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 290));

        startButton.setText("Start Monitor");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 540, 132, -1));

        notifyEveryTimeBox.setText("Notify me every new search");
        getContentPane().add(notifyEveryTimeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 190, -1));

        averagePMNotifyBox.setText("Notify me if average results exeed their threshold per minutes");
        getContentPane().add(averagePMNotifyBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 360, -1));

        singleExeedBox.setText("Notify me when a single key exeeds threshold");
        getContentPane().add(singleExeedBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 300, -1));

        perMinuteSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        getContentPane().add(perMinuteSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 380, 50, -1));

        overalSpinner.setModel(new javax.swing.SpinnerNumberModel(0, -50, 50, 1));
        getContentPane().add(overalSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 60, 30));

        overalCheckBox.setText("Notify me if overal results exeed their thresholds by a total + - %");
        getContentPane().add(overalCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 370, -1));

        pmPercentSpinner.setModel(new javax.swing.SpinnerNumberModel(100, 1, 100, 1));
        getContentPane().add(pmPercentSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, 60, -1));

        pmPercentLabel.setText("% of threshold");
        getContentPane().add(pmPercentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, 80, -1));

        pmMinutesLabel.setText("Per Minutes");
        getContentPane().add(pmMinutesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, 80, -1));

        lifetimeNotifyBox.setText("Notify me if a keyword exceeds a lifetime found limit");
        getContentPane().add(lifetimeNotifyBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, 340, -1));

        lifetimeSpinner.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), null, Float.valueOf(0.5f)));
        getContentPane().add(lifetimeSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 60, -1));

        lifetimeLabel.setText("x Threshold");
        getContentPane().add(lifetimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 460, 70, -1));

        keywordsPane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        keywordList.setModel(keywordListModel);
        keywordList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(keywordList);

        addWordButton.setText("Add");
        addWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWordButtonActionPerformed(evt);
            }
        });

        removeWordButton.setText("Remove");
        removeWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWordButtonActionPerformed(evt);
            }
        });

        keywordsLabel.setText("Keywords");

        javax.swing.GroupLayout keywordsPanelLayout = new javax.swing.GroupLayout(keywordsPanel);
        keywordsPanel.setLayout(keywordsPanelLayout);
        keywordsPanelLayout.setHorizontalGroup(
            keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keywordsPanelLayout.createSequentialGroup()
                .addGroup(keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(addWordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeWordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(keywordsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(keywordsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        keywordsPanelLayout.setVerticalGroup(
            keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, keywordsPanelLayout.createSequentialGroup()
                .addComponent(keywordsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(keywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addWordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeWordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        keywordsPane.addTab("Keywords", keywordsPanel);

        combosPanel.setToolTipText("Enter the keywords you want then enter a combo name chose mode and then press create combo button");
        combosPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboKeyList.setModel(keysComboListModel);
        jScrollPane8.setViewportView(comboKeyList);

        combosPanel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 130));

        keyComboComboBox.setModel(keysComboComboModel);
        combosPanel.add(keyComboComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 130, -1));

        newComboButton.setText("Create Combo");
        newComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newComboButtonActionPerformed(evt);
            }
        });
        combosPanel.add(newComboButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 120, 25));

        andModeRadio.setText("AND relation");
        combosPanel.add(andModeRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        orModeRadio.setText("OR relation");
        combosPanel.add(orModeRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));
        combosPanel.add(comboNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 110, -1));

        addToComboButton.setText("Add");
        addToComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToComboButtonActionPerformed(evt);
            }
        });
        combosPanel.add(addToComboButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 60, 25));

        removeFromComboButton.setText("Remove");
        removeFromComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromComboButtonActionPerformed(evt);
            }
        });
        combosPanel.add(removeFromComboButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 80, 25));

        comboKeywordsLabel.setText("Keywords to add in combo");
        combosPanel.add(comboKeywordsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, -1));

        comboNameLabel.setText("Combo name");
        combosPanel.add(comboNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 80, -1));

        createdCombosLabel.setText("Created combo's");
        combosPanel.add(createdCombosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 120, -1));

        removeComboButton.setText("Remove combo");
        removeComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeComboButtonActionPerformed(evt);
            }
        });
        combosPanel.add(removeComboButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 130, 20));

        keywordsPane.addTab("Combos", combosPanel);

        getContentPane().add(keywordsPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 350, 260));

        thresholdLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        thresholdLabel.setText("Threshold");
        getContentPane().add(thresholdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, -1, -1));

        thresholdSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        getContentPane().add(thresholdSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, -1, -1));

        keywordField.setText("Type keywords here....");
        keywordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keywordFieldMouseClicked(evt);
            }
        });
        getContentPane().add(keywordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 144, 30));

        firstSearchSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, new java.util.Date(), java.util.Calendar.HOUR_OF_DAY));
        getContentPane().add(firstSearchSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 130, -1));

        firstSearchBox.setText("1st Search since");
        getContentPane().add(firstSearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 170, -1));

        fbSearchNowBox.setText("Search from now");
        fbSearchNowBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbSearchNowBoxActionPerformed(evt);
            }
        });
        getContentPane().add(fbSearchNowBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pSitesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pSitesAllActionPerformed
       pSitesList.setSelectionInterval(0,pSitesList.getModel().getSize()-1 );
    }//GEN-LAST:event_pSitesAllActionPerformed

    private void pSitesNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pSitesNoneActionPerformed
       pSitesList.clearSelection();
    }//GEN-LAST:event_pSitesNoneActionPerformed

    private void uSitesNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uSitesNoneActionPerformed
       uSitesList.clearSelection();
    }//GEN-LAST:event_uSitesNoneActionPerformed

    private void uSitesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uSitesAllActionPerformed
        uSitesList.setSelectionInterval(0,uSitesList.getModel().getSize()-1 );
    }//GEN-LAST:event_uSitesAllActionPerformed

    private void frSitesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frSitesAllActionPerformed
        
        frSitesList.setSelectionInterval(0,frSitesList.getModel().getSize() -1);
        
    }//GEN-LAST:event_frSitesAllActionPerformed

    private void frSitesNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frSitesNoneActionPerformed
       frSitesList.clearSelection();
    }//GEN-LAST:event_frSitesNoneActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        monitorSettings set = new monitorSettings();
        Object interval=intervalSpinner.getValue();
        set.timerInterval=((Integer)interval).intValue()*60000;


      
         
      

        if(firstSearchBox.isSelected()){
            set.firstSearch=true;
            set.firstSearchDate =  (Date) firstSearchSpinner.getValue();
        }
        else if(fbSearchNowBox.isSelected()){
            set.firstSearch=true;
            set.firstSearchDate=timeHandler.getCurrentTime().getTime();
            set.twitterSearchFromNow=true;
        }else{set.firstSearch=false;}

   
        set.searchComments=commentsCheckBox.isSelected();
        set.fbToken=settings.fbToken;

       
        if(settings.twAuth==true){
            set.twitterInstance=twitter;
        }else{set.authTwitter=false;}

       set.notSettings.notifyEveryNewSearch=notifyEveryTimeBox.isSelected();
       set.notSettings.notifyWhenExceedThreshold=singleExeedBox.isSelected();

       if(averagePMNotifyBox.isSelected()){
       set.notSettings.notifyExeedAveragePM=true;
       Object tempPMVal=perMinuteSpinner.getValue();
       set.notSettings.perMinuteExeed=((Integer)tempPMVal).intValue();
       Object tempPMPercent=pmPercentSpinner.getValue();
       set.notSettings.perMinutePercent=((Integer)tempPMPercent).intValue();
       }else{set.notSettings.notifyExeedAveragePM=false;}

       if(overalCheckBox.isSelected()){
        set.notSettings.notifyExeedOveralThresholds=true;
        Object tempOVVal=overalSpinner.getValue();
        set.notSettings.overalThresholdExeed=((Integer) tempOVVal).intValue();
       }else{set.notSettings.notifyExeedOveralThresholds=false;}

       if(lifetimeNotifyBox.isSelected()){
         set.notSettings.lifetimeExceed=true;
         Object tempLFVal=lifetimeSpinner.getValue();
         set.notSettings.lifetimeExceedTimes=((Float) tempLFVal).floatValue();
       }else{set.notSettings.lifetimeExceed=false;}

       set.fbSources=this.getFbSources();
       set.twitterSources=this.getTwitterSources();
       keywordObjList.addAll(comboObjList);
       set.keys=keywordObjList;

       if(!set.keys.isEmpty()){
        if( (set.twitterSources.isEmpty()) && (set.fbSources.length==0) ){
            JOptionPane.showMessageDialog(rootPane,"No Sources Selected!!");
        }
        else{
            monitorFrame.main(set);
            this.dispose();
        }
       }
       else{JOptionPane.showMessageDialog(rootPane,"No keywords given!");}
       
       
    }//GEN-LAST:event_startButtonActionPerformed

    private void addWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWordButtonActionPerformed
               Object thresholdObj=thresholdSpinner.getValue();
               FeedObjects.keywordObject addWord = new FeedObjects.keywordObject(keywordField.getText().toLowerCase(),((Integer)thresholdObj).intValue());
                boolean alreadyExist=false;
             
               if((!addWord.getKeyword().equals(""))&&(addWord!=null)){
                   
                    for(int i=0;i<keywordObjList.size();i++){
                        if(keywordField.getText().toLowerCase().equals(keywordObjList.get(i).getKeyword().toLowerCase())){
                            alreadyExist=true;                    
                        }
                    }
                    if(alreadyExist==false){
                    keywordListModel.addElement(addWord.getKeyword()+" : "+String.valueOf(addWord.getThreshold()));

                    keywordObjList.add(addWord);
                    keywordField.setText("");
                    thresholdSpinner.setValue(0);
                    
                    }
                    else{
                      JOptionPane.showMessageDialog(rootPane, "Keyword allready exist","Keyword allready exist",2);
                    }

        }
    }//GEN-LAST:event_addWordButtonActionPerformed

    private void removeWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeWordButtonActionPerformed
        int index =keywordList.getSelectedIndex();
        if(!keywordListModel.isEmpty()){
        keywordListModel.remove(index);
        keywordObjList.remove(index);
        }

        

    }//GEN-LAST:event_removeWordButtonActionPerformed

    private void addSiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSiteButtonActionPerformed
        String entry;
         entry=JOptionPane.showInputDialog(this,"just copy the site name Example" +
                "\n www.facebook.com/\"siteName\"" , "Enter new site here", 1);
         if(entry!=null){
         if(!entry.isEmpty()){
         try{
         FileHandler.appendFile("psiteslist.txt", entry);
         pSitesListModel.addElement(entry);

         }catch(Exception e){
             JOptionPane.showMessageDialog(rootPane,"Cannot add source \n Possibly the application cannot create text file \n Check for write protection!");
             System.out.println(e.getMessage());}
    }}
    }//GEN-LAST:event_addSiteButtonActionPerformed

    private void removeSiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSiteButtonActionPerformed
        try{
        Object[] indices;

        ArrayList<String> sites = new ArrayList();
        indices=pSitesList.getSelectedValues();
        for(int i=0;i<indices.length;i++){
            pSitesListModel.removeElement(indices[i]);
        }
        
        for(int i=0;i<pSitesListModel.size();i++){
            sites.add((String) pSitesListModel.get(i));
        }
        
        FileHandler.writeFile("psiteslist.txt",sites);
    
        }catch(Exception e){System.out.println(e.getMessage());}
    }//GEN-LAST:event_removeSiteButtonActionPerformed

    private void twitterSitesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twitterSitesAllActionPerformed
       twitterPublicList.setSelectionInterval(0,twitterPublicList.getModel().getSize()-1 );
    }//GEN-LAST:event_twitterSitesAllActionPerformed

    private void twitterSitesNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twitterSitesNoneActionPerformed
        twitterPublicList.clearSelection();
    }//GEN-LAST:event_twitterSitesNoneActionPerformed

    private void addTwitterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTwitterUserActionPerformed
         String entry;
         entry=JOptionPane.showInputDialog(this,"just copy the twitter user name" , "Enter new twitter user", 1);
         if(entry!=null){
         if(!entry.isEmpty()){
         try{
         FileHandler.appendFile("twitterlist.txt", entry);
         twitterListModel.addElement(entry);
         
         }catch(Exception e){
             JOptionPane.showMessageDialog(rootPane,"Cannot add source \n Possibly the application cannot create text file \n Check for write protection!");
             System.out.println(e.getMessage());}
    }}
    }//GEN-LAST:event_addTwitterUserActionPerformed

    private void removeTwitterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTwitterUserActionPerformed
         try{
        Object[] indices;

        ArrayList<String> sites = new ArrayList();
        indices=twitterPublicList.getSelectedValues();
        for(int i=0;i<indices.length;i++){
            twitterListModel.removeElement(indices[i]);
        }

        for(int i=0;i<twitterListModel.size();i++){
            sites.add((String) twitterListModel.get(i));
        }

        FileHandler.writeFile("twitterlist.txt",sites);
    
        }catch(Exception e){System.out.println(e.getMessage());}
    }//GEN-LAST:event_removeTwitterUserActionPerformed

    private void friendsAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendsAllButtonActionPerformed
         twitterFriendList.setSelectionInterval(0,twitterFriendList.getModel().getSize()-1 );
    }//GEN-LAST:event_friendsAllButtonActionPerformed

    private void followersAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_followersAllButtonActionPerformed
         twitterFollowersList.setSelectionInterval(0,twitterFollowersList.getModel().getSize()-1 );
    }//GEN-LAST:event_followersAllButtonActionPerformed

    private void friendsNoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendsNoneButtonActionPerformed
        twitterFriendList.clearSelection();
    }//GEN-LAST:event_friendsNoneButtonActionPerformed

    private void followersNoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_followersNoneButtonActionPerformed
        twitterFollowersList.clearSelection();
    }//GEN-LAST:event_followersNoneButtonActionPerformed

    private void addToComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToComboButtonActionPerformed
            boolean alreadyExist=false;
            String word = keywordField.getText().toLowerCase();


            if((!word.equals(""))&&(word!=null)){
            for(int i=0;i<keysComboListModel.size();i++){
                        if(word.equals(keysComboListModel.get(i).toString())){
                            alreadyExist=true;
                        }
             }
            }
            if(alreadyExist==false){
                keysComboListModel.addElement(word);
                keywordField.setText("");
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Keyword allready exist","Keyword allready exist",2);
            }


    }//GEN-LAST:event_addToComboButtonActionPerformed

    private void removeFromComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromComboButtonActionPerformed
            int index=comboKeyList.getSelectedIndex();
            if(!keysComboListModel.isEmpty()){
            keysComboListModel.removeElementAt(index);
            }


    }//GEN-LAST:event_removeFromComboButtonActionPerformed

    private void newComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newComboButtonActionPerformed
            String comboName=comboNameField.getText();

            boolean isListEmpty,comboAllreadyExist=false;
            int listSize;
            listSize=keysComboListModel.size();
            
            if(listSize>1){
                isListEmpty=false;
            }else{isListEmpty=true;}

                if((!comboName.equals(""))&&(comboName!=null)){
                      for(int j=0;j<keysComboComboModel.getSize();j++){
                        if(comboName.equals(keysComboComboModel.getElementAt(j).toString())){
                            comboAllreadyExist=true;
                        }
                      }

                   if(comboAllreadyExist==false){
                    if(isListEmpty==false){
                        Object thresholdObj=thresholdSpinner.getValue();
                        FeedObjects.comboObject comboObj = new FeedObjects.comboObject(comboName,((Integer)thresholdObj).intValue());
                        for(int i=0;i<keysComboListModel.size();i++){
                            comboObj.addWord(keysComboListModel.getElementAt(i).toString());
                        }
                        comboObj.setTrueForAndFalseForOR(andModeRadio.isSelected());

                        comboObjList.add(comboObj);
                        keysComboComboModel.addElement(comboName);

                        keysComboListModel.clear();
                        comboNameField.setText("");


                    }
                    else{
                    JOptionPane.showMessageDialog(rootPane, "Keyword List is Empty","Keyword list is empty plz enter at least 2 keywords",2);
                    }
                   }
                   else{
                    JOptionPane.showMessageDialog(rootPane, "Combo name allreay exist","Combo name allready exist chose another name",2);
                   }
                }
                else{
                JOptionPane.showMessageDialog(rootPane, "Combo Name Field is Empty","Combo Name Field is Empty",2);
                }


    }//GEN-LAST:event_newComboButtonActionPerformed

    private void removeComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeComboButtonActionPerformed
                if(keyComboComboBox.getSelectedItem()!=null){
                    int index=keyComboComboBox.getSelectedIndex();
                    keysComboComboModel.removeElementAt(index);
                    comboObjList.remove(index);
                }

    }//GEN-LAST:event_removeComboButtonActionPerformed

    private void fbSearchNowBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbSearchNowBoxActionPerformed
        if(fbSearchNowBox.isSelected()){
            firstSearchBox.setEnabled(false);
            firstSearchSpinner.setEnabled(false);
        }
        else{
              firstSearchBox.setEnabled(true);
            firstSearchSpinner.setEnabled(true);
        }
    }//GEN-LAST:event_fbSearchNowBoxActionPerformed

    private void keywordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keywordFieldMouseClicked
      keywordField.setText("");
    }//GEN-LAST:event_keywordFieldMouseClicked


    public List<String> getTwitterSources(){
        List<String> temp = new ArrayList();
        int[] indices;
        indices=twitterPublicList.getSelectedIndices();
        for(int i=0;i<indices.length;i++){
            temp.add((String) twitterListModel.getElementAt(indices[i]));
        }
        indices=twitterFriendList.getSelectedIndices();
         for(int i=0;i<indices.length;i++){
            temp.add((String) twitterFriendsListModel.getElementAt(indices[i]));
        }
         indices=twitterFollowersList.getSelectedIndices();
         for(int i=0;i<indices.length;i++){
            temp.add((String) twitterFollowersListModel.getElementAt(indices[i]));
        }
        
        return temp;
    }



 

  

    public String[] getFbSources(){
         List<String> temp = new ArrayList();
        int[] indices;

        indices=uSitesList.getSelectedIndices();
        for(int i=0;i<indices.length;i++){
        temp.add(uSitelist[indices[i]].getID());
        }
        indices=frSitesList.getSelectedIndices();
        for(int i=0;i<indices.length;i++){
        temp.add(frList[indices[i]].getID());
        }
        indices=pSitesList.getSelectedIndices();
        for(int i=0;i<indices.length;i++){
        temp.add((String) pSitesListModel.get(indices[i]));

        }

        String[] sourceArray = temp.toArray(new String[0]);

        return sourceArray;

    }

       


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSiteButton;
    private javax.swing.JButton addToComboButton;
    private javax.swing.JButton addTwitterUser;
    private javax.swing.JButton addWordButton;
    private javax.swing.JRadioButton andModeRadio;
    private javax.swing.JCheckBox averagePMNotifyBox;
    private javax.swing.JList comboKeyList;
    private javax.swing.JLabel comboKeywordsLabel;
    private javax.swing.JTextField comboNameField;
    private javax.swing.JLabel comboNameLabel;
    private javax.swing.JPanel combosPanel;
    private javax.swing.JCheckBox commentsCheckBox;
    private javax.swing.JTabbedPane configPane;
    private javax.swing.JLabel createdCombosLabel;
    private javax.swing.JCheckBox fbSearchNowBox;
    private javax.swing.JPanel fbSourcesPanel;
    private javax.swing.JCheckBox firstSearchBox;
    private javax.swing.JSpinner firstSearchSpinner;
    private javax.swing.JButton followersAllButton;
    private javax.swing.JLabel followersLabel;
    private javax.swing.JButton followersNoneButton;
    private javax.swing.JButton frSitesAll;
    private javax.swing.JList frSitesList;
    private javax.swing.JButton frSitesNone;
    private javax.swing.JButton friendsAllButton;
    private javax.swing.JLabel friendsLabel;
    private javax.swing.JButton friendsNoneButton;
    private javax.swing.JLabel intervalLabel;
    private javax.swing.JSpinner intervalSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JComboBox keyComboComboBox;
    private javax.swing.JTextField keywordField;
    private javax.swing.JList keywordList;
    private javax.swing.JLabel keywordsLabel;
    private javax.swing.JTabbedPane keywordsPane;
    private javax.swing.JPanel keywordsPanel;
    private javax.swing.JLabel lifetimeLabel;
    private javax.swing.JCheckBox lifetimeNotifyBox;
    private javax.swing.JSpinner lifetimeSpinner;
    private javax.swing.JLabel minutesLabel;
    private javax.swing.ButtonGroup modeRadioGroup;
    private javax.swing.JButton newComboButton;
    private javax.swing.JCheckBox notifyEveryTimeBox;
    private javax.swing.JRadioButton orModeRadio;
    private javax.swing.JCheckBox overalCheckBox;
    private javax.swing.JSpinner overalSpinner;
    private javax.swing.JButton pSitesAll;
    private javax.swing.JList pSitesList;
    private javax.swing.JButton pSitesNone;
    private javax.swing.JSpinner perMinuteSpinner;
    private javax.swing.JLabel pmMinutesLabel;
    private javax.swing.JLabel pmPercentLabel;
    private javax.swing.JSpinner pmPercentSpinner;
    private javax.swing.JButton removeComboButton;
    private javax.swing.JButton removeFromComboButton;
    private javax.swing.JButton removeSiteButton;
    private javax.swing.JButton removeTwitterUser;
    private javax.swing.JButton removeWordButton;
    private javax.swing.JCheckBox singleExeedBox;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel thresholdLabel;
    private javax.swing.JSpinner thresholdSpinner;
    private javax.swing.JPanel twSourcesPanel;
    private javax.swing.JList twitterFollowersList;
    private javax.swing.JList twitterFriendList;
    private javax.swing.JLabel twitterListLabel;
    private javax.swing.JList twitterPublicList;
    private javax.swing.JButton twitterSitesAll;
    private javax.swing.JButton twitterSitesNone;
    private javax.swing.JButton uSitesAll;
    private javax.swing.JList uSitesList;
    private javax.swing.JButton uSitesNone;
    // End of variables declaration//GEN-END:variables

}
