-- 删除数据库
DROP DATABASE IF EXISTS vshop ;
-- 创建数据库
CREATE DATABASE vshop CHARACTER SET UTF8 ;
-- 使用数据库
USE vshop ;
-- 创建用户表
CREATE TABLE member (
   mid                  varchar(50) not null,
   password             varchar(32),
   name					varchar(50),
   email                varchar(50),
   phone                varchar(50),
   regdate                datetime,
   lastdate				datetime ,
   locked              int DEFAULT 0,
   CONSTRAINT pk_mid PRIMARY KEY (mid)
) engine="innodb";
CREATE TABLE role (
   rid                int               auto_increment,
   title              varchar(50),
   flag               varchar(50),
   constraint pk_rid primary key (rid)
) engine="innodb";
CREATE TABLE action (
   actid              int                auto_increment,
   rid                int,
   title              varchar(50),
   flag               varchar(50),
   constraint pk_actid primary key (actid) ,
   CONSTRAINT fk_rid FOREIGN KEY(rid) REFERENCES role(rid)
) engine="innodb";
CREATE TABLE member_role (
   mid                varchar(50),
   rid                int ,
   CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid) ,
   CONSTRAINT fk_rid2 FOREIGN KEY(rid) REFERENCES role(rid) 
) engine="innodb";
CREATE TABLE member_logs (
   mlid               int                auto_increment,
   mid                varchar(50),
   logindate          datetime,
   constraint PK_MEMBER_LOGS primary key (mlid) ,
   CONSTRAINT fk_mid3 FOREIGN KEY(mid) REFERENCES member(mid)
) engine="innodb";
-- 创建一级栏目表
CREATE TABLE item (
   iid                  int AUTO_INCREMENT ,
   title                varchar(50),
  CONSTRAINT pk_iid    PRIMARY KEY (IID)
) engine="innodb";
-- 创建二级栏目表
CREATE TABLE subitem (
   sid                  int AUTO_INCREMENT ,
   iid                  int,
   title                varchar(50),
 CONSTRAINT pk_sid PRIMARY KEY (sid) ,
 CONSTRAINT fk_iid FOREIGN KEY(iid) REFERENCES item(iid) ON DELETE CASCADE
) engine="innodb";
-- 创建商品表
CREATE TABLE goods (
   gid                  int AUTO_INCREMENT ,
   iid                  int,
   sid                  int,
   mid                  varchar(50),
   title                varchar(50)  not null,
   price                double  not null,
   pubdate              datetime,
   note                 text,
   delflag              int default 0,
   photo                varchar(200),
   CONSTRAINT pk_gid PRIMARY KEY (gid) ,
   CONSTRAINT fk_iid2 FOREIGN KEY(iid) REFERENCES item(iid) ON DELETE CASCADE ,
   CONSTRAINT fk_sid2 FOREIGN KEY(sid) REFERENCES subitem(sid) ON DELETE CASCADE ,
   CONSTRAINT fk_mid2 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE
) engine="innodb";
-- 创建省份表
CREATE TABLE province (
   pid                  int AUTO_INCREMENT ,
   title                varchar(50) not null,
   CONSTRAINT pk_pid PRIMARY KEY (pid)
) engine="innodb";
-- 创建城市表
CREATE TABLE city (
   cid                  int AUTO_INCREMENT ,
   pid                  int,
   title                varchar(50) not null,
   CONSTRAINT pk_cid primary key (cid) ,
   CONSTRAINT fk_pid FOREIGN KEY(pid) REFERENCES province(pid) ON DELETE CASCADE
) engine="innodb";
-- 创建联系地址
CREATE TABLE address (
   adid                 int AUTO_INCREMENT ,
   mid                  varchar(50),
   cid                  int,
   pid                  int,
   addr                 varchar(200),
   receiver             varchar(50),
   phone                varchar(50),
   deflag               int,
   CONSTRAINT pk_adid1 PRIMARY KEY (adid) ,
   CONSTRAINT fk_mid31 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE ,
   CONSTRAINT fk_pid31 FOREIGN KEY(pid) REFERENCES province(pid) ON DELETE CASCADE  ,
   CONSTRAINT fk_cid31 FOREIGN KEY(cid) REFERENCES city(cid) ON DELETE CASCADE  
) engine="innodb";
-- 创建购物车信息表
CREATE TABLE shopcar (
   scid                 int AUTO_INCREMENT ,
   mid                  varchar(50),
   gid                  int,
   amount               int,
   CONSTRAINT pk_scid PRIMARY KEY (scid) ,
   CONSTRAINT fk_mid41 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE ,
   CONSTRAINT fk_gid41 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE CASCADE
) engine="innodb";
-- 创建订单表
CREATE TABLE orders (
   oid                  int AUTO_INCREMENT ,
   mid                  varchar(50),
   address              varchar(200),
   subdate              datetime,
   price                double,
   note                 text,
   CONSTRAINT pk_oid PRIMARY KEY (oid) ,
   CONSTRAINT fk_mid51 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE 
) engine="innodb";
-- 创建订单详情
CREATE TABLE details (
   dtid                 int AUTO_INCREMENT ,
   oid                  int,
   gid                  int,
   amount            int,
   CONSTRAINT pk_dtid PRIMARY KEY (dtid) ,
   CONSTRAINT fk_oid51 FOREIGN KEY(oid) REFERENCES orders(oid) ON DELETE CASCADE ,
   CONSTRAINT fk_gid51 FOREIGN KEY(gid) REFERENCES goods(gid) ON DELETE CASCADE
) engine="innodb";

-- 增加测试数据
-- 用户名：vadmin、密码：hello
INSERT INTO member(mid,password,locked,name) VALUES ('vadmin','2E866BF58289E01583AD418F486A69DF',0,'魔乐科技') ;
-- 用户名：mldn、密码：java	
INSERT INTO member(mid,password,locked,name) VALUES ('mldn','D944055CE013ED9BBC9B010102374EB0',0,'老李') ;
-- 增加角色信息
INSERT INTO role(title,flag) VALUES ('【管理员】用户管理','member') ;
INSERT INTO role(title,flag) VALUES ('【管理员】商品管理','goods') ;
INSERT INTO role(title,flag) VALUES ('【管理员】订单管理','orders') ;
INSERT INTO role(title,flag) VALUES ('【管理员】后台管理','admin') ;
INSERT INTO role(title,flag) VALUES ('【前台用户】个人中心','center') ;
INSERT INTO role(title,flag) VALUES ('【前台用户】购物车','shopcar') ;
INSERT INTO role(title,flag) VALUES ('【前台用户】个人资料','info') ;
INSERT INTO role(title,flag) VALUES ('【前台用户】地址管理','address') ;
-- 增加管理员权限信息
INSERT INTO action(title,flag,rid) VALUES ('锁定用户','member:lock',1) ;
INSERT INTO action(title,flag,rid) VALUES ('用户列表','member:list',1) ;
INSERT INTO action(title,flag,rid) VALUES ('查看类别','item:list',2) ;
INSERT INTO action(title,flag,rid) VALUES ('商品分类','goods:item',2) ;
INSERT INTO action(title,flag,rid) VALUES ('商品列表','goods:list',2) ;
INSERT INTO action(title,flag,rid) VALUES ('商品添加','goods:add',2) ;
INSERT INTO action(title,flag,rid) VALUES ('商品修改','goods:edit',2) ;
INSERT INTO action(title,flag,rid) VALUES ('商品删除','goods:delete',2) ;
INSERT INTO action(title,flag,rid) VALUES ('订单列表','orders:list',3) ;
INSERT INTO action(title,flag,rid) VALUES ('订单查看','orders:show',3) ;
INSERT INTO action(title,flag,rid) VALUES ('管理首页','admin:show',4) ;
INSERT INTO action(title,flag,rid) VALUES ('创建订单','orders:add',3) ;
INSERT INTO action(title,flag,rid) VALUES ('修改用户密码','member:edit',1) ;
-- 增加前台用户权限信息
INSERT INTO action(title,flag,rid) VALUES ('个人中心','center:show',5) ;
INSERT INTO action(title,flag,rid) VALUES ('购物车添加','shopcar:add',6) ;
INSERT INTO action(title,flag,rid) VALUES ('购物车列表','shopcar:list',6) ;
INSERT INTO action(title,flag,rid) VALUES ('购物车删除','shopcar:delete',6) ;
INSERT INTO action(title,flag,rid) VALUES ('购物车修改','shopcar:edit',6) ;
INSERT INTO action(title,flag,rid) VALUES ('个人资料查看','info:show',7) ;
INSERT INTO action(title,flag,rid) VALUES ('个人资料修改','info:edit',7) ;
INSERT INTO action(title,flag,rid) VALUES ('修改登录密码','info:password',7) ;
INSERT INTO action(title,flag,rid) VALUES ('配送地址添加','address:add',8) ;
INSERT INTO action(title,flag,rid) VALUES ('配送地址列表','address:list',8) ;
INSERT INTO action(title,flag,rid) VALUES ('配送地址修改','address:edit',8) ;
INSERT INTO action(title,flag,rid) VALUES ('配送地址删除','address:delete',8) ;

-- 设置用户与角色的对应关系
INSERT INTO member_role(mid,rid) VALUES ('vadmin',1) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',2) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',3) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',4) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',5) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',6) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',7) ;
INSERT INTO member_role(mid,rid) VALUES ('vadmin',8) ;

INSERT INTO member_role(mid,rid) VALUES ('mldn',5) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',6) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',7) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',8) ;


INSERT INTO province (title) VALUES 
 ('其他'), ('北京'), ('重庆'), ('福建'), ('甘肃'), ('广东'),
 ('广西'), ('贵州'), ('海南'), ('河北'), ('黑龙江'), ('河南'),
 ('香港'), ('湖北'), ('湖南'), ('江苏'), ('江西'), ('吉林'),
 ('辽宁'), ('澳门'), ('内蒙古'), ('宁夏'), ('青海'), ('山东'),
 ('上海'), ('山西'), ('陕西'), ('四川'), ('台湾'), ('天津'),
 ('新疆'), ('西藏'), ('云南'), ('浙江'), ('安徽');

INSERT INTO city (pid,title) VALUES 
 (1,'其他'),(35,'合肥'), (35,'安庆'), (35,'蚌埠'), (35,'亳州'), (35,'巢湖'), (35,'滁州'),
 (35,'阜阳'), (35,'贵池'), (35,'淮北'), (35,'淮化'), (35,'淮南'), (35,'黄山'),
 (35,'九华山'), (35,'六安'), (35,'马鞍山'), (35,'宿州'), (35,'铜陵'), (35,'屯溪'),
 (35,'芜湖'), (35,'宣城'), (2,'北京'), (3,'重庆'), (4,'福州'), (4,'福安'),
 (4,'龙岩'), (4,'南平'), (4,'宁德'), (4,'莆田'), (4,'泉州'), (4,'三明'),
 (4,'邵武'), (4,'石狮'), (4,'永安'), (4,'武夷山'), (4,'厦门'), (4,'漳州'),
 (5,'兰州'), (5,'白银'), (5,'定西'), (5,'敦煌'), (5,'甘南'), (5,'金昌');

INSERT INTO city (pid,title) VALUES 
 (5,'酒泉'), (5,'临夏'), (5,'平凉'), (5,'天水'), (5,'武都'), (5,'武威'),
 (5,'西峰'), (5,'张掖'), (6,'广州'), (6,'潮阳'), (6,'潮州'), (6,'澄海'),
 (6,'东莞'), (6,'佛山'), (6,'河源'), (6,'惠州'), (6,'江门'), (6,'揭阳'),
 (6,'开平'), (6,'茂名'), (6,'梅州'), (6,'清远'), (6,'汕头'), (6,'汕尾'),
 (6,'韶关'), (6,'深圳'), (6,'顺德'), (6,'阳江'), (6,'英德'), (6,'云浮'),
 (6,'增城'), (6,'湛江'), (6,'肇庆'), (6,'中山'), (6,'珠海'), (7,'南宁');

INSERT INTO city (pid,title) VALUES 
 (7,'百色'), (7,'北海'), (7,'桂林'), (7,'防城港'), (7,'河池'), (7,'贺州'),
 (7,'柳州'), (7,'钦州'), (7,'梧州'), (7,'玉林'), (8,'贵阳'), (8,'安顺'),
 (8,'毕节'), (8,'都匀'), (8,'凯里'), (8,'六盘水'), (8,'铜仁'), (8,'兴义'),
 (8,'玉屏'), (8,'遵义'), (9,'海口'), (9,'儋县'), (9,'陵水'), (9,'琼海'),
 (9,'三亚'), (9,'五指山'), (9,'万宁'), (10,'石家庄'), (10,'保定'), (10,'北戴河'),
 (10,'沧州'), (10,'承德'), (10,'丰润'), (10,'邯郸'), (10,'衡水'), (10,'廊坊');

INSERT INTO city (pid,title) VALUES 
 (10,'南戴河'), (10,'秦皇岛'), (10,'唐山'), (10,'新城'), (10,'邢台'), (10,'张家口'),
 (11,'哈尔滨'), (11,'北安'), (11,'大庆'), (11,'大兴安岭'), (11,'鹤岗'), (11,'黑河'),
 (11,'佳木斯'), (11,'鸡西'), (11,'牡丹江'), (11,'齐齐哈尔'), (11,'七台河'), (11,'双鸭山'),
 (11,'绥化'), (11,'伊春'), (12,'郑州'), (12,'安阳'), (12,'鹤壁'), (12,'潢川'),
 (12,'焦作'), (12,'济源'), (12,'开封'), (12,'漯河'), (12,'洛阳'), (12,'南阳'), (12,'平顶山'),
 (12,'濮阳'), (12,'三门峡'), (12,'商丘'), (12,'新乡');

INSERT INTO city (pid,title) VALUES 
 (12,'信阳'), (12,'许昌'), (12,'周口'), (12,'驻马店'), (13,'香港'), (13,'九龙'),
 (13,'新界'), (14,'武汉'), (14,'恩施'), (14,'鄂州'), (14,'黄冈'), (14,'黄石'),
 (14,'荆门'), (14,'荆州'), (14,'潜江'), (14,'十堰'), (14,'随州'), (14,'武穴'), (14,'仙桃'),
 (14,'咸宁'), (14,'襄阳'), (14,'襄樊'), (14,'孝感'), (14,'宜昌'), (15,'长沙'), (15,'常德'),
 (15,'郴州'), (15,'衡阳'), (15,'怀化'), (15,'吉首'), (15,'娄底'), (15,'邵阳'), (15,'湘潭'),
 (15,'益阳'), (15,'岳阳'), (15,'永州');

INSERT INTO city (pid,title) VALUES 
 (15,'张家界'), (15,'株洲'), (16,'南京'), (16,'常熟'), (16,'常州'), (16,'海门'),
 (16,'淮安'), (16,'江都'), (16,'江阴'), (16,'昆山'), (16,'连云港'), (16,'南通'),
 (16,'启东'), (16,'沭阳'), (16,'宿迁'), (16,'苏州'), (16,'太仓'), (16,'泰州'),
 (16,'同里'), (16,'无锡'), (16,'徐州'), (16,'盐城'), (16,'扬州'), (16,'宜兴'),
 (16,'仪征'), (16,'张家港'), (16,'镇江'), (16,'周庄'), (17,'南昌'), (17,'抚州'),
 (17,'赣州'), (17,'吉安'), (17,'景德镇'), (17,'井冈山'), (17,'九江'), (17,'庐山');

INSERT INTO city (pid,title) VALUES 
 (17,'萍乡'), (17,'上饶'), (17,'新余'), (17,'宜春'), (17,'鹰潭'), (18,'长春'),
 (18,'白城'), (18,'白山'), (18,'珲春'), (18,'辽源'), (18,'梅河'), (18,'吉林'),
 (18,'四平'), (18,'松原'), (18,'通化'), (18,'延吉'), (19,'沈阳'), (19,'鞍山'),
 (19,'本溪'), (19,'朝阳'), (19,'大连'), (19,'丹东'), (19,'抚顺'), (19,'阜新'),
 (19,'葫芦岛'), (19,'锦州'), (19,'辽阳'), (19,'盘锦'), (19,'铁岭'), (19,'营口'),
 (20,'澳门'), (21,'呼和浩特'), (21,'阿拉善盟'), (21,'包头'), (21,'赤峰'), (21,'东胜');

INSERT INTO city (pid,title) VALUES 
 (21,'海拉尔'), (21,'集宁'), (21,'临河'), (21,'通辽'), (21,'乌海'), (21,'乌兰浩特'),
 (21,'锡林浩特'), (22,'银川'), (22,'固原'), (22,'石嘴山'), (22,'吴忠'), (23,'西宁'),
 (23,'德令哈'), (23,'格尔木'), (23,'共和'), (23,'海东'), (23,'海晏'), (23,'玛沁'),
 (23,'同仁'), (23,'玉树'), (24,'济南'), (24,'滨州'), (24,'兖州'), (24,'德州'),
 (24,'东营'), (24,'菏泽'), (24,'济宁'), (24,'莱芜'), (24,'聊城'), (24,'临沂'),
 (24,'蓬莱'), (24,'青岛'), (24,'曲阜'), (24,'日照'), (24,'泰安');

INSERT INTO city (pid,title) VALUES 
 (24,'潍坊'), (24,'威海'), (24,'烟台'), (24,'枣庄'), (24,'淄博'), (25,'上海'),
 (25,'崇明'), (25,'朱家角'), (26,'太原'), (26,'长治'), (26,'大同'), (26,'候马'),
 (26,'晋城'), (26,'离石'), (26,'临汾'), (26,'宁武'), (26,'朔州'), (26,'忻州'),
 (26,'阳泉'), (26,'榆次'), (26,'运城'), (27,'西安'), (27,'安康'), (27,'宝鸡'),
 (27,'汉中'), (27,'渭南'), (27,'商州'), (27,'绥德'), (27,'铜川'), (27,'咸阳'),
 (27,'延安'), (27,'榆林'), (28,'成都'), (28,'巴中'), (28,'达州'), (28,'德阳');

INSERT INTO city (pid,title) VALUES 
 (28,'都江堰'), (28,'峨眉山'), (28,'涪陵'), (28,'广安'), (28,'广元'), (28,'九寨沟'),
 (28,'康定'), (28,'乐山'), (28,'泸州'), (28,'马尔康'), (28,'绵阳'), (28,'眉山'),
 (28,'南充'), (28,'内江'), (28,'攀枝花'), (28,'遂宁'), (28,'汶川'), (28,'西昌'),
 (28,'雅安'), (28,'宜宾'), (28,'自贡'), (28,'资阳'), (29,'台北'), (29,'基隆'),
 (29,'台南'), (29,'台中'), (30,'天津'), (31,'乌鲁木齐'), (31,'阿克苏'), (31,'阿勒泰'),
 (31,'阿图什'), (31,'博乐'), (31,'昌吉'), (31,'东山'), (31,'哈密');

INSERT INTO city (pid,title) VALUES 
 (31,'和田'), (31,'喀什'), (31,'克拉玛依'), (31,'库车'), (31,'库尔勒'), (31,'奎屯'),
 (31,'石河子'), (31,'塔城'), (31,'吐鲁番'), (31,'伊宁'), (32,'拉萨'), (32,'阿里'),
 (32,'昌都'), (32,'林芝'), (32,'那曲'), (32,'日喀则'), (32,'山南'), (33,'昆明'),
 (33,'大理'), (33,'保山'), (33,'楚雄'), (33,'东川'), (33,'个旧'),
 (33,'景洪'), (33,'开远'), (33,'临沧'), (33,'丽江'), (33,'六库'), (33,'潞西'),
 (33,'曲靖'), (33,'思茅'), (33,'文山'), (33,'西双版纳'), (33,'玉溪');

INSERT INTO city (pid,title) VALUES 
 (33,'中甸'), (33,'昭通'), (34,'杭州'), (34,'安吉'), (34,'慈溪'), (34,'定海'),
 (34,'奉化'), (34,'海盐'), (34,'黄岩'), (34,'湖州'), (34,'嘉兴'), (34,'金华'),
 (34,'临安'), (34,'临海'), (34,'丽水'), (34,'宁波'), (34,'瓯海'), (34,'平湖'),
 (34,'千岛湖'), (34,'衢州'), (34,'江山'), (34,'瑞安'), (34,'绍兴'), (34,'嵊州'),
 (34,'台州'), (34,'温岭'), (34,'温州'), (34,'舟山') ;

INSERT INTO item(title) VALUES ('图书音响') ;
INSERT INTO item(title) VALUES ('汽车用品') ;
INSERT INTO item(title) VALUES ('电脑办公') ;
INSERT INTO item(title) VALUES ('手机数码') ;
INSERT INTO item(title) VALUES ('家居生活') ;

INSERT INTO subitem(title,iid) VALUES ('儿童读物',1) ;
INSERT INTO subitem(title,iid) VALUES ('计算机',1) ;
INSERT INTO subitem(title,iid) VALUES ('人物传记',1) ;
INSERT INTO subitem(title,iid) VALUES ('汽车配件',2) ;
INSERT INTO subitem(title,iid) VALUES ('汽车改造',2) ;
INSERT INTO subitem(title,iid) VALUES ('显示器',3) ;
INSERT INTO subitem(title,iid) VALUES ('主板',3) ;
INSERT INTO subitem(title,iid) VALUES ('CPU',3) ;
INSERT INTO subitem(title,iid) VALUES ('内存',3) ;
INSERT INTO subitem(title,iid) VALUES ('手机',4) ;
INSERT INTO subitem(title,iid) VALUES ('内存卡',4) ;
INSERT INTO subitem(title,iid) VALUES ('耳机',4) ;
INSERT INTO subitem(title,iid) VALUES ('家庭装饰',5) ;
INSERT INTO subitem(title,iid) VALUES ('创意生活',5) ;