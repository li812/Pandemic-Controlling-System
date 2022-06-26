-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 10, 2022 at 03:42 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pandamic_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `auth_group`
--

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_group_permissions`
--

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_permission`
--

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_permission`
--

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1, 'Can add log entry', 1, 'add_logentry'),
(2, 'Can change log entry', 1, 'change_logentry'),
(3, 'Can delete log entry', 1, 'delete_logentry'),
(4, 'Can view log entry', 1, 'view_logentry'),
(5, 'Can add permission', 2, 'add_permission'),
(6, 'Can change permission', 2, 'change_permission'),
(7, 'Can delete permission', 2, 'delete_permission'),
(8, 'Can view permission', 2, 'view_permission'),
(9, 'Can add group', 3, 'add_group'),
(10, 'Can change group', 3, 'change_group'),
(11, 'Can delete group', 3, 'delete_group'),
(12, 'Can view group', 3, 'view_group'),
(13, 'Can add user', 4, 'add_user'),
(14, 'Can change user', 4, 'change_user'),
(15, 'Can delete user', 4, 'delete_user'),
(16, 'Can view user', 4, 'view_user'),
(17, 'Can add content type', 5, 'add_contenttype'),
(18, 'Can change content type', 5, 'change_contenttype'),
(19, 'Can delete content type', 5, 'delete_contenttype'),
(20, 'Can view content type', 5, 'view_contenttype'),
(21, 'Can add session', 6, 'add_session'),
(22, 'Can change session', 6, 'change_session'),
(23, 'Can delete session', 6, 'delete_session'),
(24, 'Can view session', 6, 'view_session'),
(25, 'Can add ashaworker', 7, 'add_ashaworker'),
(26, 'Can change ashaworker', 7, 'change_ashaworker'),
(27, 'Can delete ashaworker', 7, 'delete_ashaworker'),
(28, 'Can view ashaworker', 7, 'view_ashaworker'),
(29, 'Can add cases', 8, 'add_cases'),
(30, 'Can change cases', 8, 'change_cases'),
(31, 'Can delete cases', 8, 'delete_cases'),
(32, 'Can view cases', 8, 'view_cases'),
(33, 'Can add category', 9, 'add_category'),
(34, 'Can change category', 9, 'change_category'),
(35, 'Can delete category', 9, 'delete_category'),
(36, 'Can view category', 9, 'view_category'),
(37, 'Can add district', 10, 'add_district'),
(38, 'Can change district', 10, 'change_district'),
(39, 'Can delete district', 10, 'delete_district'),
(40, 'Can view district', 10, 'view_district'),
(41, 'Can add locations', 11, 'add_locations'),
(42, 'Can change locations', 11, 'change_locations'),
(43, 'Can delete locations', 11, 'delete_locations'),
(44, 'Can view locations', 11, 'view_locations'),
(45, 'Can add login', 12, 'add_login'),
(46, 'Can change login', 12, 'change_login'),
(47, 'Can delete login', 12, 'delete_login'),
(48, 'Can view login', 12, 'view_login'),
(49, 'Can add medicalshop', 13, 'add_medicalshop'),
(50, 'Can change medicalshop', 13, 'change_medicalshop'),
(51, 'Can delete medicalshop', 13, 'delete_medicalshop'),
(52, 'Can view medicalshop', 13, 'view_medicalshop'),
(53, 'Can add servicerequest', 14, 'add_servicerequest'),
(54, 'Can change servicerequest', 14, 'change_servicerequest'),
(55, 'Can delete servicerequest', 14, 'delete_servicerequest'),
(56, 'Can view servicerequest', 14, 'view_servicerequest'),
(57, 'Can add public', 15, 'add_public'),
(58, 'Can change public', 15, 'change_public'),
(59, 'Can delete public', 15, 'delete_public'),
(60, 'Can view public', 15, 'view_public'),
(61, 'Can add notification', 16, 'add_notification'),
(62, 'Can change notification', 16, 'change_notification'),
(63, 'Can delete notification', 16, 'delete_notification'),
(64, 'Can view notification', 16, 'view_notification'),
(65, 'Can add medicinestock', 17, 'add_medicinestock'),
(66, 'Can change medicinestock', 17, 'change_medicinestock'),
(67, 'Can delete medicinestock', 17, 'delete_medicinestock'),
(68, 'Can view medicinestock', 17, 'view_medicinestock'),
(69, 'Can add kitrequest', 18, 'add_kitrequest'),
(70, 'Can change kitrequest', 18, 'change_kitrequest'),
(71, 'Can delete kitrequest', 18, 'delete_kitrequest'),
(72, 'Can view kitrequest', 18, 'view_kitrequest'),
(73, 'Can add doctor', 19, 'add_doctor'),
(74, 'Can change doctor', 19, 'change_doctor'),
(75, 'Can delete doctor', 19, 'delete_doctor'),
(76, 'Can view doctor', 19, 'view_doctor'),
(77, 'Can add docchat', 20, 'add_docchat'),
(78, 'Can change docchat', 20, 'change_docchat'),
(79, 'Can delete docchat', 20, 'delete_docchat'),
(80, 'Can view docchat', 20, 'view_docchat'),
(81, 'Can add assisatnce', 21, 'add_assisatnce'),
(82, 'Can change assisatnce', 21, 'change_assisatnce'),
(83, 'Can delete assisatnce', 21, 'delete_assisatnce'),
(84, 'Can view assisatnce', 21, 'view_assisatnce'),
(85, 'Can add ashachat', 22, 'add_ashachat'),
(86, 'Can change ashachat', 22, 'change_ashachat'),
(87, 'Can delete ashachat', 22, 'delete_ashachat'),
(88, 'Can view ashachat', 22, 'view_ashachat'),
(89, 'Can add allotedwork', 23, 'add_allotedwork'),
(90, 'Can change allotedwork', 23, 'change_allotedwork'),
(91, 'Can delete allotedwork', 23, 'delete_allotedwork'),
(92, 'Can view allotedwork', 23, 'view_allotedwork'),
(93, 'Can add alerts', 24, 'add_alerts'),
(94, 'Can change alerts', 24, 'change_alerts'),
(95, 'Can delete alerts', 24, 'delete_alerts'),
(96, 'Can view alerts', 24, 'view_alerts'),
(97, 'Can add chat', 25, 'add_chat'),
(98, 'Can change chat', 25, 'change_chat'),
(99, 'Can delete chat', 25, 'delete_chat'),
(100, 'Can view chat', 25, 'view_chat'),
(101, 'Can add worker', 26, 'add_worker'),
(102, 'Can change worker', 26, 'change_worker'),
(103, 'Can delete worker', 26, 'delete_worker'),
(104, 'Can view worker', 26, 'view_worker'),
(105, 'Can add bank', 27, 'add_bank'),
(106, 'Can change bank', 27, 'change_bank'),
(107, 'Can delete bank', 27, 'delete_bank'),
(108, 'Can view bank', 27, 'view_bank'),
(109, 'Can add order', 28, 'add_order'),
(110, 'Can change order', 28, 'change_order'),
(111, 'Can delete order', 28, 'delete_order'),
(112, 'Can view order', 28, 'view_order'),
(113, 'Can add item', 29, 'add_item'),
(114, 'Can change item', 29, 'change_item'),
(115, 'Can delete item', 29, 'delete_item'),
(116, 'Can view item', 29, 'view_item');

-- --------------------------------------------------------

--
-- Table structure for table `auth_user`
--

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_user_groups`
--

CREATE TABLE `auth_user_groups` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_user_user_permissions`
--

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `django_admin_log`
--

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext DEFAULT NULL,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) UNSIGNED NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `django_content_type`
--

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_content_type`
--

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(1, 'admin', 'logentry'),
(3, 'auth', 'group'),
(2, 'auth', 'permission'),
(4, 'auth', 'user'),
(5, 'contenttypes', 'contenttype'),
(24, 'savekerala', 'alerts'),
(23, 'savekerala', 'allotedwork'),
(22, 'savekerala', 'ashachat'),
(7, 'savekerala', 'ashaworker'),
(21, 'savekerala', 'assisatnce'),
(27, 'savekerala', 'bank'),
(8, 'savekerala', 'cases'),
(9, 'savekerala', 'category'),
(25, 'savekerala', 'chat'),
(10, 'savekerala', 'district'),
(20, 'savekerala', 'docchat'),
(19, 'savekerala', 'doctor'),
(29, 'savekerala', 'item'),
(18, 'savekerala', 'kitrequest'),
(11, 'savekerala', 'locations'),
(12, 'savekerala', 'login'),
(13, 'savekerala', 'medicalshop'),
(17, 'savekerala', 'medicinestock'),
(16, 'savekerala', 'notification'),
(28, 'savekerala', 'order'),
(15, 'savekerala', 'public'),
(14, 'savekerala', 'servicerequest'),
(26, 'savekerala', 'worker'),
(6, 'sessions', 'session');

-- --------------------------------------------------------

--
-- Table structure for table `django_migrations`
--

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_migrations`
--

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1, 'contenttypes', '0001_initial', '2022-03-02 14:46:02.965509'),
(2, 'auth', '0001_initial', '2022-03-02 14:46:03.840430'),
(3, 'admin', '0001_initial', '2022-03-02 14:46:04.221219'),
(4, 'admin', '0002_logentry_remove_auto_add', '2022-03-02 14:46:04.268063'),
(5, 'admin', '0003_logentry_add_action_flag_choices', '2022-03-02 14:46:04.314964'),
(6, 'contenttypes', '0002_remove_content_type_name', '2022-03-02 14:46:04.502413'),
(7, 'auth', '0002_alter_permission_name_max_length', '2022-03-02 14:46:04.596156'),
(8, 'auth', '0003_alter_user_email_max_length', '2022-03-02 14:46:04.674275'),
(9, 'auth', '0004_alter_user_username_opts', '2022-03-02 14:46:04.705555'),
(10, 'auth', '0005_alter_user_last_login_null', '2022-03-02 14:46:04.799407'),
(11, 'auth', '0006_require_contenttypes_0002', '2022-03-02 14:46:04.815057'),
(12, 'auth', '0007_alter_validators_add_error_messages', '2022-03-02 14:46:04.846285'),
(13, 'auth', '0008_alter_user_username_max_length', '2022-03-02 14:46:04.932998'),
(14, 'auth', '0009_alter_user_last_name_max_length', '2022-03-02 14:46:05.089251'),
(15, 'auth', '0010_alter_group_name_max_length', '2022-03-02 14:46:05.214228'),
(16, 'auth', '0011_update_proxy_permissions', '2022-03-02 14:46:05.245477'),
(17, 'auth', '0012_alter_user_first_name_max_length', '2022-03-02 14:46:05.339221'),
(18, 'savekerala', '0001_initial', '2022-03-02 14:46:08.816800'),
(19, 'sessions', '0001_initial', '2022-03-02 14:46:08.879281'),
(20, 'savekerala', '0002_category_distict', '2022-03-06 16:04:04.424017'),
(21, 'savekerala', '0003_remove_category_distict', '2022-03-09 00:50:34.223183'),
(22, 'savekerala', '0004_alter_alerts_distict', '2022-03-09 02:24:10.177288'),
(23, 'savekerala', '0005_chat_worker_remove_ashaworker_distict_and_more', '2022-03-13 01:29:41.222294'),
(24, 'savekerala', '0006_medicalshop_distict', '2022-03-13 01:52:53.376584'),
(25, 'savekerala', '0007_alter_notification_category', '2022-03-20 06:37:58.677865'),
(26, 'savekerala', '0008_alter_alerts_alertinstrction_and_more', '2022-03-20 07:10:16.859089'),
(27, 'savekerala', '0009_kitrequest_distict', '2022-03-20 07:24:57.131062'),
(28, 'savekerala', '0010_kitrequest_category', '2022-03-21 04:21:38.103712'),
(29, 'savekerala', '0011_alter_kitrequest_alotqrcode', '2022-03-21 09:11:30.122856'),
(30, 'savekerala', '0012_cases_kitrequest_alter_cases_casestatus_and_more', '2022-03-22 04:30:13.541080'),
(31, 'savekerala', '0013_cases_casedate', '2022-03-23 14:22:34.181122'),
(32, 'savekerala', '0014_bank_alter_medicalshop_licensecpy_order_item', '2022-03-25 09:38:42.181318'),
(33, 'savekerala', '0015_bank_balance', '2022-03-27 05:51:42.570600'),
(34, 'savekerala', '0016_servicerequest_worker', '2022-03-28 11:36:40.847488'),
(35, 'savekerala', '0017_alter_servicerequest_public', '2022-03-28 12:18:24.385971'),
(36, 'savekerala', '0018_alter_public_addharcopy_alter_public_pubphoto', '2022-03-29 15:24:22.689364'),
(37, 'savekerala', '0019_order_medshop', '2022-04-02 16:43:07.809830');

-- --------------------------------------------------------

--
-- Table structure for table `django_session`
--

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_session`
--

INSERT INTO `django_session` (`session_key`, `session_data`, `expire_date`) VALUES
('0jjqfqzqp8p2sgeepuepbw5c2k4o44cx', 'eyJ1c2VybmFtZSI6InBzbiIsInJvbGUiOiJzaG9wIiwiaWQiOjEyfQ:1nagic:LCbDy6sYYlVgoM4eHo7orZMTrRTpCd5iHgkEt56V_gk', '2022-04-16 16:35:18.541373'),
('9pkylj4jd4jqv2elfb4nj907tccthl5r', 'eyJ1c2VybmFtZSI6InBzbiIsInJvbGUiOiJzaG9wIiwiaWQiOjEyfQ:1nawpU:ZqN-GkfuAI0dj0adsg3Gc-LCmU978ml52Tr3p-i6vJE', '2022-04-17 09:47:28.962244'),
('9zsipkvgv1xrc0pq96vcyc5p91an5e45', 'eyJ1c2VybmFtZSI6IlBET0tMMDEiLCJyb2xlIjoiZGlzdHJpY3QiLCJpZCI6Mn0:1nTCko:JHAIdAGipsjNY9XsUsDVFeK_0m5QUl3dbnJGqiifeig', '2022-03-27 01:10:38.775499'),
('d5k3wnkslvkp15hqcc21230conl9jbcp', 'eyJ1c2VybmFtZSI6InBzbiIsInJvbGUiOiJzaG9wIiwiaWQiOjEyfQ:1nXf9q:ze-e3UGIviPMC59t_BA7R8iWu9hCaKOZMvpYovi79k4', '2022-04-08 08:18:54.197272'),
('feltkhzgc5pmzgpwu0yslieh20tim7bb', 'e30:1nX36M:wiN4mSpVSQPbA6RrDKT-T6sALhM9Dmy6FpVyRQwX9Pc', '2022-04-06 15:40:46.558330'),
('gp0lb1pfqo9l6kheb4wzh1j7enix0gqz', 'e30:1nRmSK:JMYLGy4ACQOKc_W04_mAVKhdUkwdSVGkpuQV7_PdqJA', '2022-03-23 02:53:40.404664'),
('j84demedidzyi9h3ur5a6bmpa4wpryn4', '.eJyrViotTi3KS8xNVbJSSkzJzcxT0lEqys9B5mamKFkZ1gIAMM4NdQ:1noOPq:hTBjyODEMjkqwKpDu70JhFJRY9_gDyRYmQ3G0j8FHjQ', '2022-05-24 11:52:34.103938'),
('jnwc3kj7f8xv77q788n1c0rwuv98zkjv', 'eyJ1c2VybmFtZSI6ImFsYWRtaW5AZ21haWwuY29tIiwicm9sZSI6ImRpc3RyaWN0IiwiaWQiOjI3fQ:1nnokW:MBNQRUnwmuQO0wpZony1cHFU5cTJ-RE4bmEDXanfjUc', '2022-05-22 21:47:32.805912'),
('plkqrdry36nakvjctw1erahql4nzgl3d', 'e30:1ncI4t:9dttyU_JHlkWNWDetDRByac7YudfgJfoCgfgkyVPwvI', '2022-04-21 02:40:55.223755'),
('pqya1mmqqciwel7ynjmr5gltumv1uyyf', 'eyJ1c2VybmFtZSI6IlBET0tMMDEiLCJyb2xlIjoiZGlzdHJpY3QiLCJpZCI6Mn0:1nVrzS:2-ow-J-TlR8Fay_JZYbhZYmUgJCdFZs7y8-7VciZPqE', '2022-04-03 09:36:46.945242'),
('qqsa3b4vggw35298sakq4e0mourlegm1', 'eyJ1c2VybmFtZSI6InNocDEiLCJyb2xlIjoic2hvcCIsImlkIjo0fQ:1nVF7B:BLjNJRxZhHuiM-vi9X_dy74RtWVt252XY_3mDZYn3Hs', '2022-04-01 16:06:09.289412'),
('s0b8b7gxbfa7z1mxdxqfldxlpypfnu7f', 'e30:1nQtm6:dg83AXZ5HfnnfckUl60i_keOu3b5X1Czb9_GnhRk5vE', '2022-03-20 16:30:26.143672'),
('tpz1i1zq5yz7l66836wb5u84pniw7q6w', 'e30:1ncI58:go0kE1CAkhCVu2Gpi8Ft3ED9txAJ5Z0zCd4jybFrxcs', '2022-04-21 02:41:10.517701'),
('u59zk4slzr9q1lo23v3q1u87112wvj7v', '.eJyrViotTi3KS8xNVbJSSkzJzcxT0lEqys9B5mamKFkZ1gIAMM4NdQ:1noORj:nW-OqDNcE9rJiXUs3Mx5jleR2oZFuKtxZ5pu2RLE5Bs', '2022-05-24 11:54:31.087175'),
('v5uiikld19cvewmbuinn3yha0x5upkkp', 'eyJ1c2VybmFtZSI6InNoeWFtIiwicm9sZSI6ImRpc3RyaWN0IiwiaWQiOjN9:1naIfa:EJ_pDQdoj0-4U6wqnBaMQHFW09msh50sW2Frzv4KsgM', '2022-04-15 14:54:34.917179'),
('wd1cme3zce9iga23secrn2nqgq78icdi', 'eyJ1c2VybmFtZSI6InNocDEiLCJyb2xlIjoic2hvcCIsImlkIjo0fQ:1nVPlM:v2kUMBms9OYn0fqYTmi2PfcV4xiNgnvuv-jbpPW-9jc', '2022-04-02 03:28:20.846812'),
('wunv352hsm2353m377tehq8dax3abmoi', 'eyJ1c2VybmFtZSI6InR2YWRtaW5AZ21haWwuY29tIiwicm9sZSI6ImRpc3RyaWN0IiwiaWQiOjM5fQ:1noPB1:Yv9lfz8dUzVd5HLhMTzc4CHmafCMN37LUkFWNcDCQ1M', '2022-05-24 12:41:19.669326'),
('ypni9691pplex6q1axn6odu5rand7vco', 'eyJ1c2VybmFtZSI6IlBET0tMMDEiLCJyb2xlIjoiZGlzdHJpY3QiLCJpZCI6Mn0:1nVEtY:XDZ1dlf7jC1jVPKfVHhCosBML1MtV-IoV_mqh6Q0RBU', '2022-04-01 15:52:04.867456'),
('z75qtevks9i9zo1zj44s3h8um33hhvp7', 'e30:1nUBjT:YduitP-5g3zE_Y5GIoWGFVxfzQIep-5R_MaJuenOs2g', '2022-03-29 18:17:19.411573');

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_alerts`
--

CREATE TABLE `savekerala_alerts` (
  `alertsid` int(11) NOT NULL,
  `alerttype` varchar(100) NOT NULL,
  `alertinstrction` varchar(100) NOT NULL,
  `postdt` varchar(100) NOT NULL,
  `startdt` varchar(100) NOT NULL,
  `alertstatus` varchar(100) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `distict_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_allotedwork`
--

CREATE TABLE `savekerala_allotedwork` (
  `allotid` int(11) NOT NULL,
  `workstatus` varchar(100) NOT NULL,
  `allotedate` varchar(100) NOT NULL,
  `report` varchar(100) NOT NULL,
  `ashaworker_id` int(11) DEFAULT NULL,
  `servicerequest_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_assisatnce`
--

CREATE TABLE `savekerala_assisatnce` (
  `assitid` int(11) NOT NULL,
  `date` varchar(100) NOT NULL,
  `message` varchar(100) NOT NULL,
  `cases_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_bank`
--

CREATE TABLE `savekerala_bank` (
  `bankid` int(11) NOT NULL,
  `holdername` varchar(100) NOT NULL,
  `cvv` varchar(10) NOT NULL,
  `accno` varchar(50) NOT NULL,
  `exp` varchar(50) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_cases`
--

CREATE TABLE `savekerala_cases` (
  `caseid` int(11) NOT NULL,
  `casestatus` varchar(100) NOT NULL,
  `infectionhistory` varchar(100) NOT NULL,
  `infectiontype` varchar(100) NOT NULL,
  `distict_id` int(11) DEFAULT NULL,
  `public_id` int(11) DEFAULT NULL,
  `kitrequest_id` int(11) DEFAULT NULL,
  `casedate` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_category`
--

CREATE TABLE `savekerala_category` (
  `category_id` int(11) NOT NULL,
  `categoryname` varchar(100) NOT NULL,
  `categorydesc` varchar(100) NOT NULL,
  `categorytype` varchar(100) NOT NULL,
  `categorystartdt` varchar(100) NOT NULL,
  `categorysymtoms` varchar(100) NOT NULL,
  `categoryprecotaions` varchar(100) NOT NULL,
  `categorystatus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `savekerala_category`
--

INSERT INTO `savekerala_category` (`category_id`, `categoryname`, `categorydesc`, `categorytype`, `categorystartdt`, `categorysymtoms`, `categoryprecotaions`, `categorystatus`) VALUES
(2, 'Spanish Flu', 'Highly dangerous disease  ', 'Virus outbreak', '2022-05-09', 'Cough, Cold', 'Wear mask and keep the environment hygienic.', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_chat`
--

CREATE TABLE `savekerala_chat` (
  `docchatid` int(11) NOT NULL,
  `workertype` varchar(100) NOT NULL,
  `sendertype` varchar(100) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `senddate` varchar(100) NOT NULL,
  `sendtime` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `public_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_district`
--

CREATE TABLE `savekerala_district` (
  `district_id` int(11) NOT NULL,
  `district` varchar(100) NOT NULL,
  `districtadminnm` varchar(100) NOT NULL,
  `districtadmindes` varchar(100) NOT NULL,
  `districtadmindcontact` varchar(100) NOT NULL,
  `districtadmindemail` varchar(100) NOT NULL,
  `login_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `savekerala_district`
--

INSERT INTO `savekerala_district` (`district_id`, `district`, `districtadminnm`, `districtadmindes`, `districtadmindcontact`, `districtadmindemail`, `login_id`) VALUES
(3, 'Alappuzha', 'Shyla Mohandas', 'District Collector ', '9746108475', 'aladmin@gmail.com', 27),
(4, 'Ernakulam', 'Vishnu Radhakrishnan', 'District Collector', '9123456780', 'eradmin@gmail.com', 29),
(5, 'Idukki', 'Emmanuel Francis', 'District Collector ', '8123456789', 'idadmin@gmail,com', 30),
(6, 'Kannur', 'Akshay S Anil', 'District Collector', '91276534089', 'knadmin@gmail.com', 31),
(7, 'Kasargod', 'Anil Kumar', 'District Collector', '8907654321', 'ksadmin@gmail.com', 32),
(8, 'Kollam', 'Sreedevi Mohan', 'District Collector ', '8765432109', 'kladmin@gmail.com', 33),
(9, 'Kottayam', 'Veera Ragavan', 'District Collector ', '8643210987', 'ktadmin@gmail.com', 34),
(10, 'Kozhikode', 'Akash Chandran', 'District Collector ', '8123543210', 'kzadmin@gmail.com', 35),
(11, 'Malappuram', 'Sam Pillai', 'District Collector ', '8163109563', 'maadmin@gmail.com', 36),
(12, 'Palakkad', 'Durga Menon', 'District Collector ', '9354104657', 'pladmin@gmail.com', 37),
(13, 'Pathanamthitta', 'Anima Vishnu', 'District Collector ', '8465108945', 'ptadmin@gmail.com', 38),
(14, 'Thiruvananthapuram', 'Irene Gilbert Emmanuel ', 'District Collector ', '7563901234', 'tvadmin@gmail.com', 39),
(15, 'Thrissur', 'Nasiya Ali', 'District Collector ', '9123409876', 'tsadmin@gmail.com', 40),
(16, 'Wayanad', 'Sumana Akshaj', 'District Collector ', '9456728567', 'waadmin@gmail.com', 41);

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_item`
--

CREATE TABLE `savekerala_item` (
  `itemid` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `stock_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_kitrequest`
--

CREATE TABLE `savekerala_kitrequest` (
  `kitid` int(11) NOT NULL,
  `kitstaus` varchar(100) NOT NULL,
  `requestdt` varchar(100) NOT NULL,
  `previousrequestdate` varchar(100) NOT NULL,
  `alotqrcode` varchar(100) NOT NULL,
  `workstatus` varchar(100) NOT NULL,
  `allotedate` varchar(100) NOT NULL,
  `ashaworker_id` int(11) DEFAULT NULL,
  `public_id` int(11) DEFAULT NULL,
  `distict_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_locations`
--

CREATE TABLE `savekerala_locations` (
  `location_id` int(11) NOT NULL,
  `location` varchar(100) NOT NULL,
  `distict_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `savekerala_locations`
--

INSERT INTO `savekerala_locations` (`location_id`, `location`, `distict_id`) VALUES
(4, 'Chenganoor', 3),
(5, 'Kollam', 8),
(6, 'Kunnathoor', 8),
(7, 'Karunagappally', 8),
(8, 'Kottarakkara', 8),
(9, 'Punalur', 8),
(10, 'Pathanapuram', 8),
(11, 'Chirayinkeezhu ', 14),
(12, 'Kattakkada', 14),
(13, 'Nedumangadu', 14),
(14, 'Neyyattinkara', 14),
(15, 'Varkala', 14),
(16, 'Thiruvananthapuram', 14),
(17, 'Mavelikkara', 3),
(18, 'Karthikappally', 3),
(19, 'Kuttanad ', 3),
(20, 'Ambalappuzha', 3),
(21, 'Cherthala', 3),
(22, 'Kothamangalam', 4),
(23, 'Muvattupuzha', 4),
(24, 'Kunnathunad', 4),
(25, 'Kanayannur', 4),
(26, 'Kochi', 4),
(27, 'North Paravur', 4),
(28, 'Aluva', 4),
(29, 'Peermade', 5),
(30, 'Udumbanchola', 5),
(31, 'Idukki', 5),
(32, 'Thodupuzha', 5),
(33, 'Devikulam', 5),
(34, 'Thalassery', 6),
(35, 'Iritty', 6),
(36, 'Kannur', 6),
(37, 'Taliparamba', 6),
(38, 'Payyanur', 6),
(39, 'Hosdurg', 7),
(40, 'Vellarikundu', 7),
(41, 'Kasaragod', 7),
(42, 'Manjeshwaram ', 7),
(44, 'Changanasserry', 9),
(45, 'Kottayam', 9),
(46, 'Vaikom', 9),
(47, 'Meenachil', 9),
(48, 'Kanjirappally', 9);

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_login`
--

CREATE TABLE `savekerala_login` (
  `logid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `savekerala_login`
--

INSERT INTO `savekerala_login` (`logid`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(27, 'aladmin@gmail.com', 'aladmin@gmail.com', 'district'),
(29, 'eradmin@gmail.com', 'eradmin@gmail.com', 'district'),
(30, 'idadmin@gmail,com', 'idadmin@gmail,com', 'district'),
(31, 'knadmin@gmail.com', 'knadmin@gmail.com', 'district'),
(32, 'ksadmin@gmail.com', 'ksadmin@gmail.com', 'district'),
(33, 'kladmin@gmail.com', 'kladmin@gmail.com', 'district'),
(34, 'ktadmin@gmail.com', 'ktadmin@gmail.com', 'district'),
(35, 'kzadmin@gmail.com', 'kzadmin@gmail.com', 'district'),
(36, 'maadmin@gmail.com', 'maadmin@gmail.com', 'district'),
(37, 'pladmin@gmail.com', 'pladmin@gmail.com', 'district'),
(38, 'ptadmin@gmail.com', 'ptadmin@gmail.com', 'district'),
(39, 'tvadmin@gmail.com', 'tvadmin@gmail.com', 'district'),
(40, 'tsadmin@gmail.com', 'tsadmin@gmail.com', 'district'),
(41, 'waadmin@gmail.com', 'waadmin@gmail.com', 'district'),
(42, 'tvas11@gmail.com', 'tvas11@gmail.com', 'asha');

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_medicalshop`
--

CREATE TABLE `savekerala_medicalshop` (
  `medshop_id` int(11) NOT NULL,
  `shopname` varchar(100) NOT NULL,
  `licenseno` varchar(100) NOT NULL,
  `ownerno` varchar(100) NOT NULL,
  `contactno` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `workinghrs` varchar(100) NOT NULL,
  `licensecpy` varchar(100) NOT NULL,
  `medstatus` varchar(100) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `distict_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_medicinestock`
--

CREATE TABLE `savekerala_medicinestock` (
  `stock_id` int(11) NOT NULL,
  `medicinename` varchar(100) NOT NULL,
  `medtype` varchar(100) NOT NULL,
  `medprice` varchar(100) NOT NULL,
  `medqty` varchar(100) NOT NULL,
  `meddec` varchar(100) NOT NULL,
  `medpic` varchar(100) NOT NULL,
  `medshop_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_notification`
--

CREATE TABLE `savekerala_notification` (
  `notif_id` int(11) NOT NULL,
  `notificationtitle` varchar(100) NOT NULL,
  `notification` varchar(100) NOT NULL,
  `notificdate` varchar(100) NOT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_order`
--

CREATE TABLE `savekerala_order` (
  `orderid` int(11) NOT NULL,
  `date` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `medshop_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_public`
--

CREATE TABLE `savekerala_public` (
  `pubid` int(11) NOT NULL,
  `pubname` varchar(100) NOT NULL,
  `addharno` varchar(100) NOT NULL,
  `addharcopy` varchar(100) NOT NULL,
  `pubdob` varchar(100) NOT NULL,
  `pubcontact` varchar(100) NOT NULL,
  `pubemail` varchar(100) NOT NULL,
  `pubaddress` varchar(100) NOT NULL,
  `pubjob` varchar(100) NOT NULL,
  `pubjobsector` varchar(100) NOT NULL,
  `pubjobdescrp` varchar(100) NOT NULL,
  `pubphoto` varchar(100) NOT NULL,
  `pubstatus` varchar(100) NOT NULL,
  `distict_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_servicerequest`
--

CREATE TABLE `savekerala_servicerequest` (
  `serviceid` int(11) NOT NULL,
  `isolationreason` varchar(100) NOT NULL,
  `requirement` varchar(100) NOT NULL,
  `regdate` varchar(100) NOT NULL,
  `reqdate` varchar(100) NOT NULL,
  `requeststatus` varchar(100) NOT NULL,
  `district_id` int(11) DEFAULT NULL,
  `public_id` int(11) DEFAULT NULL,
  `worker_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `savekerala_worker`
--

CREATE TABLE `savekerala_worker` (
  `workerid` int(11) NOT NULL,
  `empcode` varchar(100) NOT NULL,
  `empname` varchar(100) NOT NULL,
  `empphoto` varchar(100) NOT NULL,
  `empcontact` varchar(100) NOT NULL,
  `empemail` varchar(100) NOT NULL,
  `empstatus` varchar(100) NOT NULL,
  `empaddress` varchar(100) NOT NULL,
  `emptype` varchar(100) NOT NULL,
  `distict_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `savekerala_worker`
--

INSERT INTO `savekerala_worker` (`workerid`, `empcode`, `empname`, `empphoto`, `empcontact`, `empemail`, `empstatus`, `empaddress`, `emptype`, `distict_id`, `location_id`, `login_id`) VALUES
(6, 'TVAS11', 'Beena Nair', 'images/360_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws_ZvmIqaK.jpg', '8945628465', 'tvas11@gmail.com', 'waiting', 'Beena Nair Home', 'asha', 14, 11, 42);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auth_group`
--
ALTER TABLE `auth_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  ADD KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`);

--
-- Indexes for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`);

--
-- Indexes for table `auth_user`
--
ALTER TABLE `auth_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  ADD KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`);

--
-- Indexes for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  ADD KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`);

--
-- Indexes for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  ADD KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`);

--
-- Indexes for table `django_content_type`
--
ALTER TABLE `django_content_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`);

--
-- Indexes for table `django_migrations`
--
ALTER TABLE `django_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `django_session`
--
ALTER TABLE `django_session`
  ADD PRIMARY KEY (`session_key`),
  ADD KEY `django_session_expire_date_a5c62663` (`expire_date`);

--
-- Indexes for table `savekerala_alerts`
--
ALTER TABLE `savekerala_alerts`
  ADD PRIMARY KEY (`alertsid`),
  ADD KEY `savekerala_alerts_category_id_97264a03_fk_savekeral` (`category_id`),
  ADD KEY `savekerala_alerts_distict_id_9878a020_fk_savekeral` (`distict_id`);

--
-- Indexes for table `savekerala_allotedwork`
--
ALTER TABLE `savekerala_allotedwork`
  ADD PRIMARY KEY (`allotid`),
  ADD KEY `savekerala_allotedwo_servicerequest_id_930e62c7_fk_savekeral` (`servicerequest_id`),
  ADD KEY `savekerala_allotedwo_ashaworker_id_0ffc3f36_fk_savekeral` (`ashaworker_id`);

--
-- Indexes for table `savekerala_assisatnce`
--
ALTER TABLE `savekerala_assisatnce`
  ADD PRIMARY KEY (`assitid`),
  ADD KEY `savekerala_assisatnc_cases_id_d3a3334c_fk_savekeral` (`cases_id`),
  ADD KEY `savekerala_assisatnc_doctor_id_ab388aa5_fk_savekeral` (`doctor_id`);

--
-- Indexes for table `savekerala_bank`
--
ALTER TABLE `savekerala_bank`
  ADD PRIMARY KEY (`bankid`);

--
-- Indexes for table `savekerala_cases`
--
ALTER TABLE `savekerala_cases`
  ADD PRIMARY KEY (`caseid`),
  ADD KEY `savekerala_cases_distict_id_8420da06_fk_savekeral` (`distict_id`),
  ADD KEY `savekerala_cases_public_id_794c8295_fk_savekerala_public_pubid` (`public_id`),
  ADD KEY `savekerala_cases_kitrequest_id_29264874_fk_savekeral` (`kitrequest_id`);

--
-- Indexes for table `savekerala_category`
--
ALTER TABLE `savekerala_category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `savekerala_chat`
--
ALTER TABLE `savekerala_chat`
  ADD PRIMARY KEY (`docchatid`),
  ADD KEY `savekerala_chat_doctor_id_3192ae86_fk_savekerala_worker_workerid` (`doctor_id`),
  ADD KEY `savekerala_chat_public_id_ebe5dd5f_fk_savekerala_public_pubid` (`public_id`);

--
-- Indexes for table `savekerala_district`
--
ALTER TABLE `savekerala_district`
  ADD PRIMARY KEY (`district_id`),
  ADD KEY `savekerala_district_login_id_50403001_fk_savekerala_login_logid` (`login_id`);

--
-- Indexes for table `savekerala_item`
--
ALTER TABLE `savekerala_item`
  ADD PRIMARY KEY (`itemid`),
  ADD KEY `savekerala_item_order_id_9ea96ddd_fk_savekerala_order_orderid` (`order_id`),
  ADD KEY `savekerala_item_stock_id_a5bd4fba_fk_savekeral` (`stock_id`);

--
-- Indexes for table `savekerala_kitrequest`
--
ALTER TABLE `savekerala_kitrequest`
  ADD PRIMARY KEY (`kitid`),
  ADD KEY `savekerala_kitreques_public_id_d06f46d9_fk_savekeral` (`public_id`),
  ADD KEY `savekerala_kitreques_ashaworker_id_b5789d47_fk_savekeral` (`ashaworker_id`),
  ADD KEY `savekerala_kitreques_distict_id_027ce851_fk_savekeral` (`distict_id`),
  ADD KEY `savekerala_kitreques_category_id_57f6a33c_fk_savekeral` (`category_id`);

--
-- Indexes for table `savekerala_locations`
--
ALTER TABLE `savekerala_locations`
  ADD PRIMARY KEY (`location_id`),
  ADD KEY `savekerala_locations_distict_id_8473fdef_fk_savekeral` (`distict_id`);

--
-- Indexes for table `savekerala_login`
--
ALTER TABLE `savekerala_login`
  ADD PRIMARY KEY (`logid`);

--
-- Indexes for table `savekerala_medicalshop`
--
ALTER TABLE `savekerala_medicalshop`
  ADD PRIMARY KEY (`medshop_id`),
  ADD KEY `savekerala_medicalsh_location_id_70e2d2dc_fk_savekeral` (`location_id`),
  ADD KEY `savekerala_medicalsh_login_id_859805fa_fk_savekeral` (`login_id`),
  ADD KEY `savekerala_medicalsh_distict_id_25b76062_fk_savekeral` (`distict_id`);

--
-- Indexes for table `savekerala_medicinestock`
--
ALTER TABLE `savekerala_medicinestock`
  ADD PRIMARY KEY (`stock_id`),
  ADD KEY `savekerala_medicines_medshop_id_70487a22_fk_savekeral` (`medshop_id`);

--
-- Indexes for table `savekerala_notification`
--
ALTER TABLE `savekerala_notification`
  ADD PRIMARY KEY (`notif_id`),
  ADD KEY `savekerala_notificat_category_id_a69cefc6_fk_savekeral` (`category_id`);

--
-- Indexes for table `savekerala_order`
--
ALTER TABLE `savekerala_order`
  ADD PRIMARY KEY (`orderid`),
  ADD KEY `savekerala_order_user_id_2eb61023_fk_savekerala_public_pubid` (`user_id`),
  ADD KEY `savekerala_order_medshop_id_cc22946f_fk_savekeral` (`medshop_id`);

--
-- Indexes for table `savekerala_public`
--
ALTER TABLE `savekerala_public`
  ADD PRIMARY KEY (`pubid`),
  ADD KEY `savekerala_public_distict_id_cbbeaef1_fk_savekeral` (`distict_id`),
  ADD KEY `savekerala_public_location_id_5fade756_fk_savekeral` (`location_id`),
  ADD KEY `savekerala_public_login_id_ef611430_fk_savekerala_login_logid` (`login_id`);

--
-- Indexes for table `savekerala_servicerequest`
--
ALTER TABLE `savekerala_servicerequest`
  ADD PRIMARY KEY (`serviceid`),
  ADD KEY `savekerala_servicere_district_id_77d31b84_fk_savekeral` (`district_id`),
  ADD KEY `savekerala_servicere_public_id_70e92fc9_fk_savekeral` (`public_id`),
  ADD KEY `savekerala_servicere_worker_id_96b6a763_fk_savekeral` (`worker_id`);

--
-- Indexes for table `savekerala_worker`
--
ALTER TABLE `savekerala_worker`
  ADD PRIMARY KEY (`workerid`),
  ADD KEY `savekerala_worker_distict_id_c2636c48_fk_savekeral` (`distict_id`),
  ADD KEY `savekerala_worker_location_id_b8ef2f58_fk_savekeral` (`location_id`),
  ADD KEY `savekerala_worker_login_id_049a2aed_fk_savekerala_login_logid` (`login_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auth_group`
--
ALTER TABLE `auth_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auth_permission`
--
ALTER TABLE `auth_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;

--
-- AUTO_INCREMENT for table `auth_user`
--
ALTER TABLE `auth_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `django_content_type`
--
ALTER TABLE `django_content_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `django_migrations`
--
ALTER TABLE `django_migrations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `savekerala_alerts`
--
ALTER TABLE `savekerala_alerts`
  MODIFY `alertsid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `savekerala_allotedwork`
--
ALTER TABLE `savekerala_allotedwork`
  MODIFY `allotid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `savekerala_assisatnce`
--
ALTER TABLE `savekerala_assisatnce`
  MODIFY `assitid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `savekerala_bank`
--
ALTER TABLE `savekerala_bank`
  MODIFY `bankid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `savekerala_cases`
--
ALTER TABLE `savekerala_cases`
  MODIFY `caseid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `savekerala_category`
--
ALTER TABLE `savekerala_category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `savekerala_chat`
--
ALTER TABLE `savekerala_chat`
  MODIFY `docchatid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `savekerala_district`
--
ALTER TABLE `savekerala_district`
  MODIFY `district_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `savekerala_item`
--
ALTER TABLE `savekerala_item`
  MODIFY `itemid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `savekerala_kitrequest`
--
ALTER TABLE `savekerala_kitrequest`
  MODIFY `kitid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `savekerala_locations`
--
ALTER TABLE `savekerala_locations`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `savekerala_login`
--
ALTER TABLE `savekerala_login`
  MODIFY `logid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `savekerala_medicalshop`
--
ALTER TABLE `savekerala_medicalshop`
  MODIFY `medshop_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `savekerala_medicinestock`
--
ALTER TABLE `savekerala_medicinestock`
  MODIFY `stock_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `savekerala_notification`
--
ALTER TABLE `savekerala_notification`
  MODIFY `notif_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `savekerala_order`
--
ALTER TABLE `savekerala_order`
  MODIFY `orderid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `savekerala_public`
--
ALTER TABLE `savekerala_public`
  MODIFY `pubid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `savekerala_servicerequest`
--
ALTER TABLE `savekerala_servicerequest`
  MODIFY `serviceid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `savekerala_worker`
--
ALTER TABLE `savekerala_worker`
  MODIFY `workerid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`);

--
-- Constraints for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`);

--
-- Constraints for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  ADD CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  ADD CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `savekerala_alerts`
--
ALTER TABLE `savekerala_alerts`
  ADD CONSTRAINT `savekerala_alerts_category_id_97264a03_fk_savekeral` FOREIGN KEY (`category_id`) REFERENCES `savekerala_category` (`category_id`),
  ADD CONSTRAINT `savekerala_alerts_distict_id_9878a020_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`);

--
-- Constraints for table `savekerala_allotedwork`
--
ALTER TABLE `savekerala_allotedwork`
  ADD CONSTRAINT `savekerala_allotedwo_ashaworker_id_0ffc3f36_fk_savekeral` FOREIGN KEY (`ashaworker_id`) REFERENCES `savekerala_worker` (`workerid`),
  ADD CONSTRAINT `savekerala_allotedwo_servicerequest_id_930e62c7_fk_savekeral` FOREIGN KEY (`servicerequest_id`) REFERENCES `savekerala_servicerequest` (`serviceid`);

--
-- Constraints for table `savekerala_assisatnce`
--
ALTER TABLE `savekerala_assisatnce`
  ADD CONSTRAINT `savekerala_assisatnc_cases_id_d3a3334c_fk_savekeral` FOREIGN KEY (`cases_id`) REFERENCES `savekerala_cases` (`caseid`),
  ADD CONSTRAINT `savekerala_assisatnc_doctor_id_ab388aa5_fk_savekeral` FOREIGN KEY (`doctor_id`) REFERENCES `savekerala_worker` (`workerid`);

--
-- Constraints for table `savekerala_cases`
--
ALTER TABLE `savekerala_cases`
  ADD CONSTRAINT `savekerala_cases_distict_id_8420da06_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`),
  ADD CONSTRAINT `savekerala_cases_kitrequest_id_29264874_fk_savekeral` FOREIGN KEY (`kitrequest_id`) REFERENCES `savekerala_kitrequest` (`kitid`),
  ADD CONSTRAINT `savekerala_cases_public_id_794c8295_fk_savekerala_public_pubid` FOREIGN KEY (`public_id`) REFERENCES `savekerala_public` (`pubid`);

--
-- Constraints for table `savekerala_chat`
--
ALTER TABLE `savekerala_chat`
  ADD CONSTRAINT `savekerala_chat_doctor_id_3192ae86_fk_savekerala_worker_workerid` FOREIGN KEY (`doctor_id`) REFERENCES `savekerala_worker` (`workerid`),
  ADD CONSTRAINT `savekerala_chat_public_id_ebe5dd5f_fk_savekerala_public_pubid` FOREIGN KEY (`public_id`) REFERENCES `savekerala_public` (`pubid`);

--
-- Constraints for table `savekerala_district`
--
ALTER TABLE `savekerala_district`
  ADD CONSTRAINT `savekerala_district_login_id_50403001_fk_savekerala_login_logid` FOREIGN KEY (`login_id`) REFERENCES `savekerala_login` (`logid`);

--
-- Constraints for table `savekerala_item`
--
ALTER TABLE `savekerala_item`
  ADD CONSTRAINT `savekerala_item_order_id_9ea96ddd_fk_savekerala_order_orderid` FOREIGN KEY (`order_id`) REFERENCES `savekerala_order` (`orderid`),
  ADD CONSTRAINT `savekerala_item_stock_id_a5bd4fba_fk_savekeral` FOREIGN KEY (`stock_id`) REFERENCES `savekerala_medicinestock` (`stock_id`);

--
-- Constraints for table `savekerala_kitrequest`
--
ALTER TABLE `savekerala_kitrequest`
  ADD CONSTRAINT `savekerala_kitreques_ashaworker_id_b5789d47_fk_savekeral` FOREIGN KEY (`ashaworker_id`) REFERENCES `savekerala_worker` (`workerid`),
  ADD CONSTRAINT `savekerala_kitreques_category_id_57f6a33c_fk_savekeral` FOREIGN KEY (`category_id`) REFERENCES `savekerala_category` (`category_id`),
  ADD CONSTRAINT `savekerala_kitreques_distict_id_027ce851_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`),
  ADD CONSTRAINT `savekerala_kitreques_public_id_d06f46d9_fk_savekeral` FOREIGN KEY (`public_id`) REFERENCES `savekerala_public` (`pubid`);

--
-- Constraints for table `savekerala_locations`
--
ALTER TABLE `savekerala_locations`
  ADD CONSTRAINT `savekerala_locations_distict_id_8473fdef_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`);

--
-- Constraints for table `savekerala_medicalshop`
--
ALTER TABLE `savekerala_medicalshop`
  ADD CONSTRAINT `savekerala_medicalsh_distict_id_25b76062_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`),
  ADD CONSTRAINT `savekerala_medicalsh_location_id_70e2d2dc_fk_savekeral` FOREIGN KEY (`location_id`) REFERENCES `savekerala_locations` (`location_id`),
  ADD CONSTRAINT `savekerala_medicalsh_login_id_859805fa_fk_savekeral` FOREIGN KEY (`login_id`) REFERENCES `savekerala_login` (`logid`);

--
-- Constraints for table `savekerala_medicinestock`
--
ALTER TABLE `savekerala_medicinestock`
  ADD CONSTRAINT `savekerala_medicines_medshop_id_70487a22_fk_savekeral` FOREIGN KEY (`medshop_id`) REFERENCES `savekerala_medicalshop` (`medshop_id`);

--
-- Constraints for table `savekerala_notification`
--
ALTER TABLE `savekerala_notification`
  ADD CONSTRAINT `savekerala_notificat_category_id_a69cefc6_fk_savekeral` FOREIGN KEY (`category_id`) REFERENCES `savekerala_category` (`category_id`);

--
-- Constraints for table `savekerala_order`
--
ALTER TABLE `savekerala_order`
  ADD CONSTRAINT `savekerala_order_medshop_id_cc22946f_fk_savekeral` FOREIGN KEY (`medshop_id`) REFERENCES `savekerala_medicalshop` (`medshop_id`),
  ADD CONSTRAINT `savekerala_order_user_id_2eb61023_fk_savekerala_public_pubid` FOREIGN KEY (`user_id`) REFERENCES `savekerala_public` (`pubid`);

--
-- Constraints for table `savekerala_public`
--
ALTER TABLE `savekerala_public`
  ADD CONSTRAINT `savekerala_public_distict_id_cbbeaef1_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`),
  ADD CONSTRAINT `savekerala_public_location_id_5fade756_fk_savekeral` FOREIGN KEY (`location_id`) REFERENCES `savekerala_locations` (`location_id`),
  ADD CONSTRAINT `savekerala_public_login_id_ef611430_fk_savekerala_login_logid` FOREIGN KEY (`login_id`) REFERENCES `savekerala_login` (`logid`);

--
-- Constraints for table `savekerala_servicerequest`
--
ALTER TABLE `savekerala_servicerequest`
  ADD CONSTRAINT `savekerala_servicere_district_id_77d31b84_fk_savekeral` FOREIGN KEY (`district_id`) REFERENCES `savekerala_district` (`district_id`),
  ADD CONSTRAINT `savekerala_servicere_public_id_70e92fc9_fk_savekeral` FOREIGN KEY (`public_id`) REFERENCES `savekerala_public` (`pubid`),
  ADD CONSTRAINT `savekerala_servicere_worker_id_96b6a763_fk_savekeral` FOREIGN KEY (`worker_id`) REFERENCES `savekerala_worker` (`workerid`);

--
-- Constraints for table `savekerala_worker`
--
ALTER TABLE `savekerala_worker`
  ADD CONSTRAINT `savekerala_worker_distict_id_c2636c48_fk_savekeral` FOREIGN KEY (`distict_id`) REFERENCES `savekerala_district` (`district_id`),
  ADD CONSTRAINT `savekerala_worker_location_id_b8ef2f58_fk_savekeral` FOREIGN KEY (`location_id`) REFERENCES `savekerala_locations` (`location_id`),
  ADD CONSTRAINT `savekerala_worker_login_id_049a2aed_fk_savekerala_login_logid` FOREIGN KEY (`login_id`) REFERENCES `savekerala_login` (`logid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
