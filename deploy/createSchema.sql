
    create table FCBK.APPLICATION_USERS (
        APUS_ID varchar(20) not null,
        primary key (APUS_ID)
    );

    create table FCBK.APPLICATION_USER_CREDENTIALS (
        AUCR_ID varchar(20) not null,
        AUCR_NAME varchar(20),
        AUCR_PASSWORD varchar(20),
        AUCR_USERNAME varchar(20),
        AUCR_APUS_ID varchar(20),
        primary key (AUCR_ID)
    );

    create table FCBK.APPLICATION_USER_KEYWORDS (
        AUKE_APUS_ID varchar(20),
        AUKE_KEYW_ID varchar(20),
        primary key (AUKE_APUS_ID, AUKE_KEYW_ID)
    );

    create table FCBK.APPLICATION_USER_SOURCES (
        APSO_SOUR_ID varchar(255),
        APSO_APUS_ID varchar(20),
        primary key (APSO_APUS_ID, APSO_SOUR_ID)
    );

    create table FCBK.CHECKINS (
        CHEC_MESSAGE varchar(255) not null,
        FCBK_POST_ID varchar(255) not null,
        CHEC_PLACE_ID varchar(20),
        primary key (FCBK_POST_ID)
    );

    create table FCBK.COMMENTS (
        COMM_ID varchar(50) not null,
        COMM_CREATED_DATE datetime,
        COMM_MESSAGE varchar(255),
        FCBK_POST_ID varchar(255),
        USER_ID varchar(255),
        primary key (COMM_ID)
    );

    create table FCBK.EVENTS (
        EVEN_ID varchar(255) not null,
        EVEN_DESCRIPTION varchar(255),
        EVEN_END_DATE datetime,
        EVEN_LOCATION varchar(255),
        EVEN_NAME varchar(255),
        EVEN_START_DATE datetime,
        EVEN_USER_ID varchar(255),
        EVEN_VENU_ID varchar(20),
        primary key (EVEN_ID)
    );

    create table FCBK.EVENT_POSTS (
        FCBK_POST_ID varchar(255) not null,
        EVEN_ID varchar(255),
        primary key (FCBK_POST_ID)
    );

    create table FCBK.FACEBOOK_ACCESS_TOKENS (
        FTOK_ID varchar(20) not null,
        FTOK_VALUE varchar(250),
        FBAC_ID varchar(20),
        primary key (FTOK_ID)
    );

    create table FCBK.FACEBOOK_ACCOUNTS (
        FBAC_ID varchar(20) not null,
        FBAC_EMAIL varchar(30),
        APUS_ID varchar(20),
        primary key (FBAC_ID)
    );

    create table FCBK.FACEBOOK_ACCOUNT_FRIENDS (
        FBAC_ID varchar(20),
        FCFR_ID varchar(20),
        primary key (FCFR_ID, FBAC_ID)
    );

    create table FCBK.FACEBOOK_DOWNLOADS (
        FCDW_ID varchar(20) not null,
        FCDW_DATE datetime,
        APUS_ID varchar(20),
        primary key (FCDW_ID)
    );

    create table FCBK.FACEBOOK_DOWNLOAD_OPTIONS (
        FDOP_ID varchar(20) not null,
        FDOP_DOWNLOAD_INTERVAL varchar(50),
        FDOP_FACEBOOK_ACCOUNT varchar(20),
        primary key (FDOP_ID)
    );

    create table FCBK.FACEBOOK_FRIENDS (
        FCFR_ID varchar(20) not null,
        FCFR_NAME varchar(50),
        primary key (FCFR_ID)
    );

    create table FCBK.FACEBOOK_POSTS (
        FCBK_POST_ID varchar(255) not null,
        FCBK_POST_CREATED_DATE datetime,
        FCBK_POST_USER_ID varchar(255),
        primary key (FCBK_POST_ID)
    );

    create table FCBK.FACEBOOK_POST_CRITERIAS (
        FPCR_CRIT_ID varchar(20),
        FPCR_POST_ID varchar(255),
        primary key (FPCR_CRIT_ID, FPCR_POST_ID)
    );

    create table FCBK.FACEBOOK_POST_DOWNLOADS (
        FCDW_ID varchar(20),
        FCBK_POST_ID varchar(255),
        primary key (FCDW_ID, FCBK_POST_ID)
    );

    create table FCBK.FACEBOOK_POST_KEYWORD_RESULTS (
        FKRE_ID varchar(20) not null,
        KEYW_ID varchar(20),
        FCBK_POST_ID varchar(255),
        KESE_ID varchar(20),
        APUS_ID varchar(20),
        primary key (FKRE_ID)
    );

    create table FCBK.GROUPS (
        GROU_ID varchar(255) not null,
        GROU_DESCRIPTION varchar(255),
        GROU_LINK varchar(255),
        GROU_NAME varchar(255),
        GROU_UPDATED_TIME datetime,
        primary key (GROU_ID)
    );

    create table FCBK.GROUP_POSTS (
        FCBK_POST_ID varchar(255) not null,
        GROU_ID varchar(255),
        primary key (FCBK_POST_ID)
    );

    create table FCBK.KEYWORDS (
        KEYW_ID varchar(20) not null,
        KEYW_VALUE varchar(20),
        primary key (KEYW_ID)
    );

    create table FCBK.KEYWORD_SEARCHES (
        KESE_ID varchar(20) not null,
        KESE_DATE datetime,
        APUS_ID varchar(20),
        primary key (KESE_ID)
    );

    create table FCBK.LINKS (
        LINK_DESCRIPTION longtext,
        LINK_ICON_URL longtext,
        LINK_LINK_URL longtext,
        LINK_MESSAGE longtext,
        LINK_NAME varchar(255),
        LINK_PICTURE longtext,
        FCBK_POST_ID varchar(255) not null,
        primary key (FCBK_POST_ID)
    );

    create table FCBK.NOTES (
        NOTE_MESSAGE varchar(255),
        NOTE_SUBJECT varchar(255),
        FCBK_POST_ID varchar(255) not null,
        primary key (FCBK_POST_ID)
    );

    create table FCBK.PAGES (
        PAGE_ID varchar(255) not null,
        PAGE_CATEGORY varchar(255),
        PAGE_LINK varchar(255),
        PAGE_CITY varchar(255),
        PAGE_COUNTRY varchar(255),
        PAGE_STREET varchar(255),
        PAGE_ZIP varchar(255),
        PAGE_NAME varchar(255),
        primary key (PAGE_ID)
    );

    create table FCBK.PAGE_POSTS (
        FCBK_POST_ID varchar(255) not null,
        PAGE_ID varchar(255),
        primary key (FCBK_POST_ID)
    );

    create table FCBK.PHOTOS (
        PHOT_ICON varchar(255),
        PHOT_LINK varchar(255),
        PHOT_NAME varchar(255),
        PHOT_PICTURE varchar(255),
        FCBK_POST_ID varchar(255) not null,
        primary key (FCBK_POST_ID)
    );

    create table FCBK.PLACES (
        PLAC_ID varchar(20) not null,
        PLAC_LATITUDE varchar(20),
        PLAC_LONGITUDE varchar(20),
        PLAC_NAME varchar(255),
        PLAC_NUMBER_0F_CHECKINS integer,
        PLAC_PCAT_ID varchar(20),
        primary key (PLAC_ID)
    );

    create table FCBK.PLACE_CATEGORIES (
        PCAT_ID varchar(20) not null,
        PCAT_DESCRIPTION varchar(255),
        primary key (PCAT_ID)
    );

    create table FCBK.PLACE_CRITERIAS (
        PLCR_PLAC_ID varchar(20),
        PLCR_CRIT_ID varchar(20),
        primary key (PLCR_CRIT_ID, PLCR_PLAC_ID)
    );

    create table FCBK.PLACE_FACEBOOK_POSTS (
        PLFP_PLAC_ID varchar(20),
        PLFP_FBPO_ID varchar(255),
        primary key (PLFP_PLAC_ID, PLFP_FBPO_ID)
    );

    create table FCBK.PUBLIC_SOURCE_CRITERIAS (
        PSCR_ID varchar(20) not null,
        PSCR_VALUE varchar(20),
        primary key (PSCR_ID)
    );

    create table FCBK.SOURCES (
        SOUR_ID varchar(255) not null,
        SRCT_ID varchar(255),
        primary key (SOUR_ID)
    );

    create table FCBK.SOURCE_FACEBOOK_POSTS (
        SRFP_SOURCE_ID varchar(255),
        SRFP_POST_ID varchar(255),
        primary key (SRFP_POST_ID, SRFP_SOURCE_ID)
    );

    create table FCBK.SOURCE_TYPES (
        SRCT_ID varchar(255) not null,
        SRCT_TYPE varchar(255),
        primary key (SRCT_ID)
    );

    create table FCBK.STATUS_MESSAGES (
        STME_MESSAGE varchar(255),
        FCBK_POST_ID varchar(255) not null,
        primary key (FCBK_POST_ID)
    );

    create table FCBK.USERS (
        USER_ID varchar(255) not null,
        USER_FIRST_NAME varchar(255),
        USER_HOME_TOWN varchar(255),
        USER_LAST_NAME varchar(255),
        USER_LOCATION varchar(255),
        USER_MIDDLE_NAME varchar(255),
        USER_NAME varchar(255),
        USER_USERNAME varchar(255),
        primary key (USER_ID)
    );

    create table FCBK.VENUES (
        VENU_ID varchar(20) not null,
        VENU_CITY varchar(20),
        VENU_COUNTRY varchar(20),
        VENU_LATITUDE varchar(20),
        PLAC_LONGITUDE varchar(20),
        VENU_STATE varchar(20),
        VENU_STREET varchar(20),
        VENU_ZIP varchar(20),
        primary key (VENU_ID)
    );

    create table FCBK.VIDEOS (
        VIDE_DESCRIPTION longtext,
        VIDE_NAME longtext,
        VIDE_PICTURE longtext,
        VIDE_SOURCE longtext,
        FCBK_POST_ID varchar(255) not null,
        primary key (FCBK_POST_ID)
    );

    alter table FCBK.APPLICATION_USER_CREDENTIALS 
        add index FK853BEF9729821FC0 (AUCR_APUS_ID), 
        add constraint FK853BEF9729821FC0 
        foreign key (AUCR_APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.APPLICATION_USER_KEYWORDS 
        add index FK922788CF61BA3798 (AUKE_KEYW_ID), 
        add constraint FK922788CF61BA3798 
        foreign key (AUKE_KEYW_ID) 
        references FCBK.KEYWORDS (KEYW_ID);

    alter table FCBK.APPLICATION_USER_KEYWORDS 
        add index FK922788CF445405AB (AUKE_APUS_ID), 
        add constraint FK922788CF445405AB 
        foreign key (AUKE_APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.APPLICATION_USER_SOURCES 
        add index FKFECEF5D3C8D466E8 (APSO_APUS_ID), 
        add constraint FKFECEF5D3C8D466E8 
        foreign key (APSO_APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.APPLICATION_USER_SOURCES 
        add index FKFECEF5D3B80740E9 (APSO_SOUR_ID), 
        add constraint FKFECEF5D3B80740E9 
        foreign key (APSO_SOUR_ID) 
        references FCBK.SOURCES (SOUR_ID);

    alter table FCBK.CHECKINS 
        add index FK8A4125E687C14B65 (FCBK_POST_ID), 
        add constraint FK8A4125E687C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.CHECKINS 
        add index FK8A4125E6CB812316 (CHEC_PLACE_ID), 
        add constraint FK8A4125E6CB812316 
        foreign key (CHEC_PLACE_ID) 
        references FCBK.PLACES (PLAC_ID);

    alter table FCBK.COMMENTS 
        add index FKABDCDF4719833B (USER_ID), 
        add constraint FKABDCDF4719833B 
        foreign key (USER_ID) 
        references FCBK.USERS (USER_ID);

    alter table FCBK.COMMENTS 
        add index FKABDCDF487C14B65 (FCBK_POST_ID), 
        add constraint FKABDCDF487C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.EVENTS 
        add index FK7A9AD5193925C915 (EVEN_VENU_ID), 
        add constraint FK7A9AD5193925C915 
        foreign key (EVEN_VENU_ID) 
        references FCBK.VENUES (VENU_ID);

    alter table FCBK.EVENTS 
        add index FK7A9AD519548B2116 (EVEN_USER_ID), 
        add constraint FK7A9AD519548B2116 
        foreign key (EVEN_USER_ID) 
        references FCBK.USERS (USER_ID);

    alter table FCBK.EVENT_POSTS 
        add index FKD1921DAE9907E7B9 (EVEN_ID), 
        add constraint FKD1921DAE9907E7B9 
        foreign key (EVEN_ID) 
        references FCBK.EVENTS (EVEN_ID);

    alter table FCBK.EVENT_POSTS 
        add index FKD1921DAE8242E8FA (FCBK_POST_ID), 
        add constraint FKD1921DAE8242E8FA 
        foreign key (FCBK_POST_ID) 
        references FCBK.EVENTS (EVEN_ID);

    alter table FCBK.EVENT_POSTS 
        add index FKD1921DAE87C14B65 (FCBK_POST_ID), 
        add constraint FKD1921DAE87C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.FACEBOOK_ACCESS_TOKENS 
        add index FKB42E3F1C2D5705B1 (FBAC_ID), 
        add constraint FKB42E3F1C2D5705B1 
        foreign key (FBAC_ID) 
        references FCBK.FACEBOOK_ACCOUNTS (FBAC_ID);

    alter table FCBK.FACEBOOK_ACCOUNTS 
        add index FKD00A113F273CF05C (APUS_ID), 
        add constraint FKD00A113F273CF05C 
        foreign key (APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.FACEBOOK_ACCOUNT_FRIENDS 
        add index FK5AAFC9CAEC8797C6 (FCFR_ID), 
        add constraint FK5AAFC9CAEC8797C6 
        foreign key (FCFR_ID) 
        references FCBK.FACEBOOK_FRIENDS (FCFR_ID);

    alter table FCBK.FACEBOOK_ACCOUNT_FRIENDS 
        add index FK5AAFC9CA2D5705B1 (FBAC_ID), 
        add constraint FK5AAFC9CA2D5705B1 
        foreign key (FBAC_ID) 
        references FCBK.FACEBOOK_ACCOUNTS (FBAC_ID);

    alter table FCBK.FACEBOOK_DOWNLOADS 
        add index FKEC5C9E92273CF05C (APUS_ID), 
        add constraint FKEC5C9E92273CF05C 
        foreign key (APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.FACEBOOK_DOWNLOAD_OPTIONS 
        add index FKAB216620137A0969 (FDOP_FACEBOOK_ACCOUNT), 
        add constraint FKAB216620137A0969 
        foreign key (FDOP_FACEBOOK_ACCOUNT) 
        references FCBK.FACEBOOK_ACCOUNTS (FBAC_ID);

    alter table FCBK.FACEBOOK_POSTS 
        add index FK67B2C4FA1BF3C075 (FCBK_POST_USER_ID), 
        add constraint FK67B2C4FA1BF3C075 
        foreign key (FCBK_POST_USER_ID) 
        references FCBK.USERS (USER_ID);

    alter table FCBK.FACEBOOK_POST_CRITERIAS 
        add index FK1B4D9E8E184878FB (FPCR_CRIT_ID), 
        add constraint FK1B4D9E8E184878FB 
        foreign key (FPCR_CRIT_ID) 
        references FCBK.PUBLIC_SOURCE_CRITERIAS (PSCR_ID);

    alter table FCBK.FACEBOOK_POST_CRITERIAS 
        add index FK1B4D9E8E1986D958 (FPCR_POST_ID), 
        add constraint FK1B4D9E8E1986D958 
        foreign key (FPCR_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.FACEBOOK_POST_DOWNLOADS 
        add index FK52A9DA853A4932C9 (FCDW_ID), 
        add constraint FK52A9DA853A4932C9 
        foreign key (FCDW_ID) 
        references FCBK.FACEBOOK_DOWNLOADS (FCDW_ID);

    alter table FCBK.FACEBOOK_POST_DOWNLOADS 
        add index FK52A9DA8587C14B65 (FCBK_POST_ID), 
        add constraint FK52A9DA8587C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.FACEBOOK_POST_KEYWORD_RESULTS 
        add index FKD13F0D9A273CF05C (APUS_ID), 
        add constraint FKD13F0D9A273CF05C 
        foreign key (APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.FACEBOOK_POST_KEYWORD_RESULTS 
        add index FKD13F0D9A179A371D (KESE_ID), 
        add constraint FKD13F0D9A179A371D 
        foreign key (KESE_ID) 
        references FCBK.KEYWORD_SEARCHES (KESE_ID);

    alter table FCBK.FACEBOOK_POST_KEYWORD_RESULTS 
        add index FKD13F0D9A44A32249 (KEYW_ID), 
        add constraint FKD13F0D9A44A32249 
        foreign key (KEYW_ID) 
        references FCBK.KEYWORDS (KEYW_ID);

    alter table FCBK.FACEBOOK_POST_KEYWORD_RESULTS 
        add index FKD13F0D9A87C14B65 (FCBK_POST_ID), 
        add constraint FKD13F0D9A87C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.GROUP_POSTS 
        add index FKF87161D3825D6CDF (FCBK_POST_ID), 
        add constraint FKF87161D3825D6CDF 
        foreign key (FCBK_POST_ID) 
        references FCBK.GROUPS (GROU_ID);

    alter table FCBK.GROUP_POSTS 
        add index FKF87161D387C14B65 (FCBK_POST_ID), 
        add constraint FKF87161D387C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.GROUP_POSTS 
        add index FKF87161D3FCAB9947 (GROU_ID), 
        add constraint FKF87161D3FCAB9947 
        foreign key (GROU_ID) 
        references FCBK.GROUPS (GROU_ID);

    alter table FCBK.KEYWORD_SEARCHES 
        add index FK401A120C273CF05C (APUS_ID), 
        add constraint FK401A120C273CF05C 
        foreign key (APUS_ID) 
        references FCBK.APPLICATION_USERS (APUS_ID);

    alter table FCBK.LINKS 
        add index FK451579987C14B65 (FCBK_POST_ID), 
        add constraint FK451579987C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.NOTES 
        add index FK47056A187C14B65 (FCBK_POST_ID), 
        add constraint FK47056A187C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.PAGE_POSTS 
        add index FKB47DDA03A70AFF91 (FCBK_POST_ID), 
        add constraint FKB47DDA03A70AFF91 
        foreign key (FCBK_POST_ID) 
        references FCBK.PAGES (PAGE_ID);

    alter table FCBK.PAGE_POSTS 
        add index FKB47DDA0387C14B65 (FCBK_POST_ID), 
        add constraint FKB47DDA0387C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.PAGE_POSTS 
        add index FKB47DDA03DFF6DCBB (PAGE_ID), 
        add constraint FKB47DDA03DFF6DCBB 
        foreign key (PAGE_ID) 
        references FCBK.PAGES (PAGE_ID);

    alter table FCBK.PHOTOS 
        add index FK8C9F7C8187C14B65 (FCBK_POST_ID), 
        add constraint FK8C9F7C8187C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.PLACES 
        add index FK8CD13C4C6D393758 (PLAC_PCAT_ID), 
        add constraint FK8CD13C4C6D393758 
        foreign key (PLAC_PCAT_ID) 
        references FCBK.PLACE_CATEGORIES (PCAT_ID);

    alter table FCBK.PLACE_CRITERIAS 
        add index FK3B0B437C11A623AD (PLCR_CRIT_ID), 
        add constraint FK3B0B437C11A623AD 
        foreign key (PLCR_CRIT_ID) 
        references FCBK.PUBLIC_SOURCE_CRITERIAS (PSCR_ID);

    alter table FCBK.PLACE_CRITERIAS 
        add index FK3B0B437CE00513CF (PLCR_PLAC_ID), 
        add constraint FK3B0B437CE00513CF 
        foreign key (PLCR_PLAC_ID) 
        references FCBK.PLACES (PLAC_ID);

    alter table FCBK.PLACE_FACEBOOK_POSTS 
        add index FK31787529FDE1C6A (PLFP_FBPO_ID), 
        add constraint FK31787529FDE1C6A 
        foreign key (PLFP_FBPO_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.PLACE_FACEBOOK_POSTS 
        add index FK31787529458892A (PLFP_PLAC_ID), 
        add constraint FK31787529458892A 
        foreign key (PLFP_PLAC_ID) 
        references FCBK.PLACES (PLAC_ID);

    alter table FCBK.SOURCES 
        add index FKB2482798C8209B40 (SRCT_ID), 
        add constraint FKB2482798C8209B40 
        foreign key (SRCT_ID) 
        references FCBK.SOURCE_TYPES (SRCT_ID);

    alter table FCBK.SOURCE_FACEBOOK_POSTS 
        add index FK79BABD9E5BA91A08 (SRFP_POST_ID), 
        add constraint FK79BABD9E5BA91A08 
        foreign key (SRFP_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.SOURCE_FACEBOOK_POSTS 
        add index FK79BABD9EB2C917A5 (SRFP_SOURCE_ID), 
        add constraint FK79BABD9EB2C917A5 
        foreign key (SRFP_SOURCE_ID) 
        references FCBK.SOURCES (SOUR_ID);

    alter table FCBK.STATUS_MESSAGES 
        add index FK10B7AEF987C14B65 (FCBK_POST_ID), 
        add constraint FK10B7AEF987C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);

    alter table FCBK.VIDEOS 
        add index FK96E56F5887C14B65 (FCBK_POST_ID), 
        add constraint FK96E56F5887C14B65 
        foreign key (FCBK_POST_ID) 
        references FCBK.FACEBOOK_POSTS (FCBK_POST_ID);
