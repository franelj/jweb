CREATE DATABASE  IF NOT EXISTS `jweb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jweb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jweb
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `idaddress` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idaddress`),
  UNIQUE KEY `idaddress_UNIQUE` (`idaddress`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (5,'FRANEL','Julie','25 avenue de Sceaux','','78000','VERSAILLES',NULL,6),(6,'FRANEL','Julie','25 avenue de Sceaux','','78000','VERSAILLES',NULL,9),(7,'FRANEL','Julie','25 avenue de Sceaux','','78000','VERSAILLES',NULL,10);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `idauthors` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `resume` text NOT NULL,
  PRIMARY KEY (`idauthors`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Cécile','Duquenne','<p>C&eacute;cile Duquenne est n&eacute;e en 1988. Auteur amateur de fantasy, fantastique et jeunesse, elle est &eacute;galement libraire en dilettante, &eacute;tudiante en japonais la plupart du temps, pirate de l&#39;espace dans ses r&ecirc;ves les plus fous et sorci&egrave;re &agrave; Poudlard dans une r&eacute;alit&eacute; alternative. Elle aime les sushis, la couleur rouge et le th&eacute; sans sucre, par contre elle d&eacute;teste la betterave, l&#39;hypocrisie, et le caf&eacute; mal fait. Mais pour la couleur rouge, vous vous en doutiez peut-&ecirc;tre, vu le titre...</p>'),(3,'Robert','Jordan','<p>Robert Jordan est n&eacute; &agrave; Charleston en Caroline du Sud. Il a servi deux ans pendant la guerre du Vi&ecirc;t Nam (de 1968 &agrave; 1970) dans l&#39;arm&eacute;e am&eacute;ricaine, ce qui lui a valu plusieurs d&eacute;corations militaires. Apr&egrave;s son retour du Vi&ecirc;t Nam, il &eacute;tudie la physique dans une &eacute;cole militaire. Une fois dipl&ocirc;m&eacute;, il travaille pour l&#39;U.S. Navy en tant qu&#39;ing&eacute;nieur sp&eacute;cialis&eacute; dans le nucl&eacute;aire. Il commence &agrave; &eacute;crire en 1977.</p><p>D&#39;apr&egrave;s l&#39;auteur, le nom de plume Robert Jordan ne vient pas du personnage principal du livre Pour qui sonne le glas d&#39;Ernest Hemingway, mais bien d&#39;une liste qu&#39;il a compos&eacute;e &agrave; partir de ses initiales. Sous d&#39;autres noms de plume, il a &eacute;galement &eacute;crit des romans historiques (sign&eacute;s Reagan O&#39;Neal) et des westerns (sign&eacute;s Jackson O&#39;Reilly).</p><p>Le 23 mars 2006, il a annonc&eacute; &ecirc;tre atteint d&#39;une maladie orpheline rare et grave, l&#39;amylose, le traitement qu&#39;il suivait pouvait lui laisser une esp&eacute;rance de vie moyenne de 4 ans. Mais le 16 septembre 2007, Robert Jordan a finalement &eacute;t&eacute; emport&eacute; par la maladie.</p><p>Son &oelig;uvre la plus connue est La Roue du temps.</p>'),(4,'David','Eddings','<p>David Eddings, n&eacute; en 1931 dans l&#39;Etat de Washington, a publi&eacute; son premier roman en 1973. D&#39;abord employ&eacute; chez Boeing, il d&eacute;missionna, fit un petit d&eacute;tour par l&#39;enseignement, puis se retrouva... directeur d&#39;un supermarch&eacute; &agrave; Denver. Refroidi par un hold-up suivi d&#39;une fusillade, il abandonna son poste, revint chez lui, &agrave; Spokane, et d&eacute;cida de se consacrer &agrave; la litt&eacute;rature.</p><p>Leigh Eddings, son &eacute;pouse, qui avait commenc&eacute; une carri&egrave;re dans l&#39;arm&eacute;e de l&#39;air, collaborait depuis toujours &agrave; ses romans. Elle s&#39;occupait plus particuli&egrave;rement des personnages f&eacute;minins et de la fin des romans ! Et cela fonctionnait &agrave; merveille puisque David Eddings est best-seller depuis 20 ans aux USA et a &eacute;galement d&eacute;clench&eacute; une v&eacute;ritable passion &agrave; l&#39;&eacute;tranger, notamment en France avec ses deux cycles cultes : La Belgariade et La Mallor&eacute;e.&nbsp;</p><p>Le c&eacute;l&egrave;bre couple-roi de la fantasy a de nouveau figur&eacute; sur les listes des best-sellers avec Le R&eacute;veil des anciens dieux, premier volume de la t&eacute;tralogie Les R&ecirc;veurs.<br />Leigh Eddings s&#39;est &eacute;teinte en f&eacute;vrier 2007 &agrave; l&#39;&acirc;ge de 69 ans, suivi en 2009 par son &eacute;poux &acirc;g&eacute; de soixante-dix-sept ans.</p>'),(5,'Robin','Hobb','<p>Elle a commenc&eacute; &agrave; &eacute;crire sous le pseudonyme de Megan Lindholm pour des revues en 1971, ainsi que des ouvrages dont on commence &agrave; trouver des versions traduites en fran&ccedil;ais. Elle a connu son plus grand succ&egrave;s avec la premi&egrave;re trilogie du cycle de l&#39;Assassin royal (The Farseer Trilogy) en 1995.</p><p>Elle est mari&eacute;e &agrave; un p&ecirc;cheur et m&egrave;re de quatre enfants. Elle affectionne particuli&egrave;rement les chiens. Cela a sans doute influenc&eacute; l&#39;attirance de Fitz (h&eacute;ros du cycle de l&#39;Assassin royal) pour les canins. Elle a aussi pass&eacute; une partie de son enfance en Alaska.</p><p>Certains auteurs &eacute;crivent sous diff&eacute;rents pseudonymes afin de pouvoir &eacute;crire dans des genres diff&eacute;rents. Les ouvrages sign&eacute;s Robin Hobb et Megan Lindholm s&#39;inscrivent principalement dans le domaine du m&eacute;di&eacute;val-fantastique. La distinction se fait par des approches diff&eacute;rentes du genre.</p>'),(7,'Patricia','Briggs','<p>Patricia Briggs menait une vie parfaitement ordinaire jusqu&rsquo;&agrave; ce qu&rsquo;elle apprenne &agrave; lire. &Agrave; partir de ce moment-l&agrave;, ses apr&egrave;s-midi se d&eacute;roul&egrave;rent &agrave; dos de dragon ou &agrave; la recherche d&rsquo;&eacute;p&eacute;es magiques, quand ce n&rsquo;&eacute;tait pas &agrave; cheval dans les Rocheuses. Dipl&ocirc;m&eacute;e en histoire et en allemand, elle est professeur et auteur. Elle vit avec sa famille dans le Nord-Ouest Pacifique.</p>'),(8,'Jim','Butcher','<p>Jim Butcher est expert en arts martiaux depuis quinze ans, dompteur de chevaux, cascadeur, escrimeur&hellip; Il vit dans le Missouri avec sa femme, son fils et un chien de garde particuli&egrave;rement vicieux. La s&eacute;rie Les Dossiers Dresden a &eacute;t&eacute; adapt&eacute;e pour la t&eacute;l&eacute;vision en 2007.</p>'),(9,'Gail Z.','Martin','<p>Gail Z. Martin avait cinq ans lorsqu&rsquo;elle a &eacute;crit sa premi&egrave;re histoire&hellip; de fant&ocirc;mes ! De longues ann&eacute;es plus tard, elle r&eacute;alise son r&ecirc;ve et rencontre un succ&egrave;s imm&eacute;diat avec cette saga, les Chroniques du N&eacute;cromancien, nourrie par sa passion pour la Fantasy, l&rsquo;Histoire et les esprits&hellip;</p>'),(10,'Terry','Goodkind','<p>Terry Goodkind est le prodige de la Fantasy am&eacute;ricaine. Son cycle de L&rsquo;&Eacute;p&eacute;e de V&eacute;rit&eacute; est un best-seller international vendu &agrave; plus de 20 millions d&rsquo;exemplaires. Pour la premi&egrave;re fois depuis Terry Brooks, un auteur a r&eacute;ussi l&rsquo;exploit de rassembler tous les publics sous sa banni&egrave;re. Tra&icirc;trise, aventure, intrigue, amour, tous les ingr&eacute;dients sont r&eacute;unis dans ce cycle pour en faire la plus grande fresque de Fantasy depuis Tolkien.</p>'),(11,'Peter V.','Brett','<p>Peter V. Brett (&laquo; Peat &raquo; pour ses amis) imagine des r&eacute;cits de Fantasy depuis toujours. L&rsquo;Homme-rune est son premier roman et d&eacute;j&agrave; un classique mondial au succ&egrave;s foudroyant. Imm&eacute;diatement passionnant et unique en son genre, il est riche d&rsquo;&eacute;motions universelles, de personnages inoubliables, et d&rsquo;une intrigue impossible &agrave; l&acirc;cher.</p>'),(16,'Kristen','Britain','<p>Kristen Britain a &eacute;t&eacute; garde forestier pour les parcs nationaux avant de s&rsquo;installer dans le Maine (&Eacute;tats-Unis). Elle vit dans une cabane en rondins o&ugrave; elle se livre &agrave; ses activit&eacute;s favorites : l&rsquo;&eacute;criture, la lecture, l&rsquo;illustration, la guitare, l&rsquo;&eacute;quitation, la r&ecirc;verie&hellip; Cette s&eacute;rie est d&eacute;j&agrave; un &eacute;clatant succ&egrave;s.</p>');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `idbooks` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `serie` varchar(255) NOT NULL,
  `idauthor` int(11) NOT NULL,
  `date` varchar(45) NOT NULL,
  `summary` text NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `editor` varchar(255) NOT NULL,
  `file` varchar(255) NOT NULL,
  PRIMARY KEY (`idbooks`),
  UNIQUE KEY `idproducts_UNIQUE` (`idbooks`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (2,'Lady Bang and The Jack','Les Foulards Rouges',1,'2014-02-19','<p>Plongez avec Lara dans l&rsquo;enfer de Bagne, plan&egrave;te-prison o&ugrave; le danger se cache partout, au c&oelig;ur de chacun de ses sinistres habitants, et m&ecirc;me derri&egrave;re chaque goutte d&rsquo;eau, chaque ressource naturelle de cette terre irradi&eacute;e.<br />&nbsp;<br />Sur Bagne, Lara traverse les &eacute;tendues d&eacute;sertiques pour remplir ses contrats et ses missions. Car Lara est une Foulard Rouge, appel&eacute;e &agrave; faire r&eacute;gner la loi &agrave; grand renfort de balles. Et sur cette plan&egrave;te-prison o&ugrave; les deux-tiers de la population sont des hommes, anciens violeurs ou psychopathes, c&rsquo;est une vraie chance pour une jeune femme comme elle de ne pas avoir fini dans un bordel. En plus, elle fait son boulot plut&ocirc;t bien &ndash; on la surnomme m&ecirc;me Lady Bang. Mais Lara n&rsquo;a pas obtenu ce job par hasard &ndash; tout comme elle n&rsquo;a pas atterri dans cet enfer par hasard. Elle doit tout &ccedil;a &agrave; quelqu&rsquo;un en particulier, quelqu&rsquo;un &agrave; qui elle en veut profond&eacute;ment... et qui, pourtant, a peut-&ecirc;tre quelque chose de nouveau &agrave; lui offrir, une chose qui n&rsquo;a pas de prix. Acceptera-t-elle de baisser un peu sa garde pour &eacute;couter ce que son envoy&eacute;, le myst&eacute;rieux Renaud, a &agrave; lui proposer ?</p>','9782820513991',0.00,'Bragelonne','1402-foulards_org.jpg'),(3,'Lil du Monde ','La Roue du Temps',3,'2012-03-23','<p><em>La Roue du Temps tourne et les &Acirc;ges naissent et meurent, laissant dans leur sillage des souvenirs destin&eacute;s &agrave; devenir des l&eacute;gendes.</em></p><p>C&rsquo;est la Nuit de l&rsquo;Hiver dans la contr&eacute;e de Deux-Rivi&egrave;res et, en ce soir de f&ecirc;te, l&rsquo;excitation des villageois est &agrave; son comble. C&rsquo;est alors qu&rsquo;arrivent trois &eacute;trangers comme le jeune Rand et ses amis d&rsquo;enfance Mat et Perrin n&rsquo;en avaient jamais vu : une dame noble et fascinante nomm&eacute;e Moiraine, son robuste compagnon et un trouv&egrave;re.&nbsp;De quoi leur faire oublier ce cavalier sombre et sinistre aper&ccedil;u dans les bois, dont la cape ne bougeait pas en plein vent&hellip;</p><p>&#8203;Mais, quand une horde de monstres sanguinaires d&eacute;ferle et met le village &agrave; feu et &agrave; sang, la myst&eacute;rieuse Moiraine devine qu&rsquo;ils recherchaient quelqu&rsquo;un : pour les trois amis l&rsquo;heure est venue de partir. Car la Roue du Temps interdit aux jeunes gens de fl&acirc;ner trop longtemps sur les routes du destin...</p>','9782352945567',25.00,'Bragelonne','1203-roue1_org.jpg'),(4,'Le Pion Blanc des Présages','La Belgariade',4,'2008-02-20','<p>Et les dieux cr&eacute;&egrave;rent l&#39;homme, et chaque dieu choisit son peuple. Ah ! Que le monde &eacute;tait jeune, que les myst&egrave;res &eacute;taient limpides ! Mais Torak, le dieu jaloux, vola l&#39;Orbe d&#39;Aldur, le joyau vivant fa&ccedil;onn&eacute; par l&#39;a&icirc;n&eacute; des dieux, et ce fut la guerre. Le f&eacute;lon fut ch&acirc;ti&eacute; ; &agrave; Cthol Mishrak, la Cit&eacute; de la Nuit, il dort toujours d&#39;un long sommeil hant&eacute; par la souffrance.<br />Le fleuve des si&egrave;cles a pass&eacute; sur les royaumes du Ponant. Les livres des pr&eacute;sages ne parlent plus qu&#39;aux initi&eacute;s, mais ils sont formels : Torak va s&#39;&eacute;veiller. Et justement l&#39;Orbe dispara&icirc;t pour la seconde fois. Que le maudit la trouve &agrave; son r&eacute;veil et il &eacute;tablira son empire sur toutes choses.</p><p>Belgarath le sorcier parviendra-t-il &agrave; conjurer le sort ? Dans cette partie d&#39;&eacute;checs cosmique, il a r&eacute;ussi &agrave; pr&eacute;server une pi&egrave;ce ma&icirc;tresse : le dernier descendant des Gardiens de l&#39;Orbe, d&eacute;sign&eacute; par les pr&eacute;sages, mais qui n&#39;est encore qu&#39;un petit gar&ccedil;on jet&eacute; sur les routes par une venteuse nuit d&#39;automne. Un simple pion, et si vuln&eacute;rable...</p>','9782266174657',7.70,'Pocket','9782266174657.jpg'),(5,'L\'apprenti assassin','L\'Assassin Royal',5,'2005-12-23','<p>Lorsque le jeune Fitz est conduit &agrave; la cour des Six-Duch&eacute;s, il ne sait pas encore que sa vie - et celle du royaume tout entier - va s&#39;en trouver boulevers&eacute;e. Le roi-servant Chevalerie, p&egrave;re de cet enfant ill&eacute;gitime, devra renoncer au tr&ocirc;ne pour ne pas entacher la r&eacute;putation de la famille royale... Et nombreux sont les pr&eacute;tendants &agrave; la succession... Fitz se retrouve isol&eacute; au centre d&#39;un univers qu&#39;il ne conna&icirc;t pas. En quoi le Vif, cette &eacute;trange magie qu&#39;il d&eacute;couvre et qui lui permet de communiquer avec les animaux, est-elle si dangereuse ? Et pour quelle raison le roi Subtil fait-il appel &agrave; lui pour lui enseigner une forme &eacute;trange de diplomatie : l&#39;art de tuer ? Poisons, magies et lames effil&eacute;es vont bien vite devenir le quotidien du jeune b&acirc;tard princier...</p>','9782290352625',8.50,'Pygmalion','61AnmbV1frL.jpg'),(6,'L\'appel de la lune','Mercy Thompson',7,'2008-11-07','<p>&laquo; Les loups-garous peuvent &ecirc;tre dangereux si vous vous mettez en travers de leur chemin. Ils ont un talent extraordinaire pour dissimuler leur v&eacute;ritable nature aux yeux des humains. Mais moi, je ne suis pas tout &agrave; fait humaine. &raquo;</p><p>En effet, Mercy Thompson n&rsquo;est pas une fille des plus banales.&nbsp;M&eacute;canicienne dans le Montana, c&rsquo;est une dure &agrave; cuire qui n&rsquo;h&eacute;site pas &agrave; mettre les mains dans le cambouis et &agrave; sortir les griffes quand le danger frappe &agrave; sa porte.&nbsp;Mais ce n&rsquo;est pas tout : son voisin tr&egrave;s sexy est le chef de meute d&rsquo;une bande de loups-garous, le minibus qu&rsquo;elle bricole en ce moment appartient &agrave; un vampire, et la vieille dame tr&egrave;s digne qui lui rend visite vient jeter des sorts sur son garage.</p><p>Au c&oelig;ur de ce monde des cr&eacute;atures de la nuit, Mercy se trouve m&ecirc;l&eacute;e &agrave; une d&eacute;licate affaire de meurtre et d&rsquo;enl&egrave;vement&hellip;</p>','9782811200411',7.10,'Milady','81rfRIMOjDL._SL1500_.jpg'),(7,'Les Furies de Calderon','Codex Aléra',8,'2010-02-19','<p>Le sort du royaume repose sur les &eacute;paules d&rsquo;un gar&ccedil;on qui n&rsquo;a aucun pouvoir&hellip;</p><p>Depuis mille ans, les habitants d&rsquo;Al&eacute;ra repoussent les peuplades sanguinaires qui ran&ccedil;onnent le monde en usant de leur relation particuli&egrave;re avec les furies &ndash; les forces &eacute;l&eacute;mentaires de la terre, de l&rsquo;air, du feu, de l&rsquo;eau, du bois et du m&eacute;tal. Mais dans la lointaine vall&eacute;e de Calderon, Tavi ne ma&icirc;trise encore aucun &eacute;l&eacute;ment, &agrave; son grand d&eacute;sespoir. &Agrave; quinze ans, il n&rsquo;a toujours pas de furie du vent pour l&rsquo;aider &agrave; voler, ou de furie du feu pour allumer ses lampes.<br />Pourtant, lorsque les f&eacute;roces Marats font leur retour dans la vall&eacute;e, le courage et l&rsquo;ing&eacute;niosit&eacute; de Tavi vont se r&eacute;v&eacute;ler une force bien plus cruciale que n&rsquo;importe quelle furie. Une force qui pourrait lui permettre d&rsquo;alt&eacute;rer le cours de la guerre&hellip;</p>','9782352943617',22.40,'Bragelonne','calderong.jpg'),(8,'L\'invocateur','Chroniques du Nécromancien',9,'2009-11-06','<p>Le monde du prince Martris Drayke est brusquement plong&eacute; dans le chaos le jour o&ugrave; son fr&egrave;re assassine leur p&egrave;re et s&rsquo;empare du tr&ocirc;ne. Contraint de fuir, soutenu par une poign&eacute;e de fid&egrave;les compagnons, Martris n&rsquo;aura de cesse de venger son p&egrave;re et de r&eacute;tablir son honneur.<br />Alors que les vivants se liguent contre lui, Martris d&eacute;couvre qu&rsquo;il pourrait bien &ecirc;tre l&rsquo;h&eacute;ritier d&rsquo;un don rare et effrayant. Il devra apprendre &agrave; dompter ses pouvoirs magiques naissants afin de lever une arm&eacute;e d&rsquo;alli&eacute;s&hellip; recrut&eacute;s parmi les morts !</p><p>Les Chroniques du N&eacute;cromancien, dont voici le premier tome, est une s&eacute;rie captivante o&ugrave; se m&ecirc;lent la mort et la vengeance, la magie et les myst&egrave;res de l&rsquo;au-del&agrave;.</p>','9782811202071',22.40,'Bragelonne','71cY5YPH7AL.jpg'),(9,'La première leçon du sorcier','L\'Epée de Vérité',10,'2003-01-28','<p>Jusqu&rsquo;&agrave; ce que Richard Cypher sauve cette belle inconnue des griffes de ses poursuivants, il vivait paisiblement dans la for&ecirc;t. Elle ne consent &agrave; lui dire que son nom : Kahlan. Mais lui sait d&eacute;j&agrave;, au premier regard, qu&rsquo;il ne pourra plus la quitter. Car d&eacute;sormais, le danger r&ocirc;de en Hartland. Des cr&eacute;atures monstrueuses suivent les pas de l&rsquo;&eacute;trang&egrave;re. Seul Zedd, son ami le vieil ermite, peut lui venir en aide&hellip; en bouleversant son destin. Richard devra porter l&rsquo;&Eacute;p&eacute;e de V&eacute;rit&eacute; et s&rsquo;opposer aux forces de Darken Rahl, le mage dictateur. Ainsi commence une extraordinaire qu&ecirc;te &agrave; travers les t&eacute;n&egrave;bres. Au nom de l&rsquo;amour. A n&rsquo;importe quel prix.</p>','9782914370334',25.00,'Bragelonne','71E9InJoekL.jpg'),(10,'L\'Homme-Rune','Le Cycle des Démons',11,'2009-10-02','<p>Il y a parfois de tr&egrave;s bonnes raisons d&rsquo;avoir peur du noir&hellip;<br />Dans le monde du jeune Arlen, d&egrave;s que le soleil se couche, les d&eacute;mons sortent de terre et d&eacute;vorent les &ecirc;tres vivants. Le seul espoir de survie : s&rsquo;abriter derri&egrave;re des runes magiques qui repoussent ces monstres et prier pour qu&rsquo;elles tiennent jusqu&rsquo;aux premi&egrave;res lueurs de l&rsquo;aube. Seule une poign&eacute;e de Messagers bravent la nuit au p&eacute;ril de leur vie pour relier les hameaux dont les habitants ne s&rsquo;&eacute;loignent jamais.<br />Mais lorsqu&rsquo;une terrible trag&eacute;die le frappe, le jeune Arlen d&eacute;cide qu&rsquo;il ne veut plus vivre dans la peur : il quitte sa ferme et part sur les routes en qu&ecirc;te d&rsquo;un moyen de se battre contre les d&eacute;mons et de les vaincre.</p>','9782352944928',19.30,'Bragelonne','1104-demons1-b_org.jpg'),(11,'Bagne','Les Foulards Rouges',1,'2015-01-21','<p>Sur Bagne, Lara traverse les &eacute;tendues d&eacute;sertiques pour remplir ses contrats. Car Lara est une Foulard Rouge, appel&eacute;e &agrave; faire r&eacute;gner la loi &agrave; grand renfort de balles. Et sur cette plan&egrave;te-prison o&ugrave; les deux-tiers de la population sont des hommes, anciens violeurs ou psychopathes, c&rsquo;est une vraie chance pour une jeune femme comme elle de ne pas avoir fini dans un bordel.<br />&nbsp;<br />En plus, elle fait plut&ocirc;t bien son boulot &ndash; on la surnomme m&ecirc;me Lady Bang. Mais Lara n&rsquo;a pas obtenu ce job par hasard &ndash; tout comme elle n&rsquo;a pas atterri dans cet enfer par hasard. Elle doit tout &ccedil;a &agrave; quelqu&rsquo;un en particulier, &agrave; qui elle en veut profond&eacute;ment... et qui, pourtant, a quelque chose &agrave; lui offrir &ndash; une chose qui n&rsquo;a pas de prix. Lara acceptera-t-elle de baisser un peu sa garde et de se lier &agrave; de dangereux criminels comme le myst&eacute;rieux Renaud ? Si elle veut reprendre son destin en main et ne pas finir ses jours ici, elle n&rsquo;aura pas vraiment le choix...</p>','9782820520289',4.99,'Bragelonne','1501-foulards-s1_org.jpg'),(12,'La Grande Quête','La Roue du Temps',3,'2012-03-23','<p><em>La Roue du Temps tourne et les &Acirc;ges naissent et meurent, laissant dans leur sillage des souvenirs destin&eacute;s &agrave; devenir des l&eacute;gendes.</em></p><p>&Agrave; peine arriv&eacute; &agrave; la forteresse de Fal Dara, le jeune Rand n&rsquo;a d&eacute;j&agrave; qu&rsquo;une id&eacute;e en t&ecirc;te : fuir les noirs secrets qu&rsquo;il a appris sur lui-m&ecirc;me.<br />Moiraine, la puissante magicienne qui seule pourrait lui fournir des r&eacute;ponses, l&rsquo;&eacute;vite d&eacute;sormais comme la peste. Rand sait qu&rsquo;il devrait quitter ces lieux, partir o&ugrave; personne ne le conna&icirc;t et o&ugrave; il ne pourrait faire aucun mal.<br />Mais il est trop tard : les Aes Sedai l&rsquo;ont enferm&eacute; et son seul espoir d&rsquo;obtenir de l&rsquo;aide &agrave; pr&eacute;sent est Egwene, la femme qu&rsquo;il pensait &eacute;pouser un jour&hellip; et qui est en train de devenir elle-m&ecirc;me une Aes Sedai.</p>','9782352945574',25.00,'Bragelonne','1203-roue2_org.jpg'),(13,'Le Dragon Réincarné','La Roue du Temps',3,'2012-07-13','<p><em>La Roue du Temps tourne et les &Acirc;ges naissent et meurent, laissant dans leur sillage des souvenirs destin&eacute;s &agrave; devenir des l&eacute;gendes.</em></p><p>Rand al&rsquo;Thor se r&eacute;signe peu &agrave; peu &agrave; son sort : il est le Dragon R&eacute;incarn&eacute;, que &ccedil;a lui plaise ou non, et il devra honorer son rendez-vous avec le T&eacute;n&eacute;breux &ndash; une sanglante affaire de gloire, d&rsquo;honneur et de salut du monde.<br />Totalement d&eacute;pass&eacute; par ses nouveaux pouvoirs, il d&eacute;cide alors de laisser ses amis derri&egrave;re lui et de partir seul pour Tear, la fabuleuse cit&eacute; o&ugrave; l&rsquo;attend Callandor, l&rsquo;&eacute;p&eacute;e l&eacute;gendaire qu&rsquo;il est cens&eacute; brandir lors de l&rsquo;Ultime Bataille. C&rsquo;est l&agrave; que les fils du destin se noueront une fois pour toutes&hellip;</p>','9782352945864',25.00,'Bragelonne','1207-roue-temps3_org.jpg'),(14,'Un Lever de Ténèbres','La Roue du Temps',3,'2012-11-23','<p><em>La Roue du Temps tourne et les &Acirc;ges naissent et meurent, laissant dans leur sillage des souvenirs destin&eacute;s &agrave; devenir des l&eacute;gendes.</em><br />&nbsp;<br />Forteresse r&eacute;put&eacute;e imprenable depuis des mill&eacute;naires, la Pierre de Tear est tomb&eacute;e, conquise par Rand al&rsquo;Thor et un groupe d&rsquo;Aiels, ces terribles guerriers du d&eacute;sert qui se voilent le visage pour combattre.<br />D&eacute;sormais en possession de l&rsquo;&eacute;p&eacute;e mythique Callandor, Rand peut clamer &agrave; la face du monde qu&rsquo;il est le Dragon R&eacute;incarn&eacute;.<br />Un grand pas en avant, certes, mais vers quelle destin&eacute;e ? Cens&eacute; sauver le monde, le jeune homme doit d&rsquo;abord apprendre &agrave; ma&icirc;triser le saidin.<br />H&eacute;las, cette moiti&eacute; masculine du Pouvoir de l&rsquo;Unique est une arme &agrave; double tranchant. Car elle condamne &agrave; la folie et &agrave; la mort les hommes &agrave; qui elle offre la gloire et la puissance&hellip;</p>','9782352946090',25.00,'Bragelonne','1211-roue-temps4_org.jpg'),(15,'Cavalier Vert','Cavalier Vert',16,'2014-05-21','<p>Karigan G&rsquo;ladheon, jeune fille &eacute;prise d&rsquo;aventure, s&rsquo;enfuit apr&egrave;s avoir &eacute;t&eacute; exclue de son &eacute;cole pour avoir d&eacute;fi&eacute; en duel le fils d&rsquo;un gouverneur de province. Elle croise alors un Cavalier Vert, l&rsquo;un des l&eacute;gendaires messagers du roi qui lui demande dans un dernier souffle de porter un message &agrave; son souverain. Sans m&ecirc;me prendre connaissance de la missive, elle fait le serment de la remettre en mains propres, scellant ainsi son destin, car elle est soudain magiquement investie de la mission qu&rsquo;elle vient d&rsquo;accepter : devenir un Cavalier Vert. D&egrave;s lors, traqu&eacute;e par des assassins au service d&rsquo;un myst&eacute;rieux sorcier, Karigan ne peut compter que sur sa fid&egrave;le monture et les myst&eacute;rieux pouvoirs qu&rsquo;elle va se d&eacute;couvrir&hellip;</p>','9782352947479',23.00,'Bragelonne','1405-cavalier1-n_org.jpg');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `idcomments` int(11) NOT NULL AUTO_INCREMENT,
  `idauthor` int(11) NOT NULL,
  `authorname` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idbook` int(11) NOT NULL,
  PRIMARY KEY (`idcomments`),
  UNIQUE KEY `idcomments_UNIQUE` (`idcomments`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,6,'julie','abcdef comment test !','2014-12-24 16:33:07',2),(2,8,'admin','test !','2014-12-24 18:12:32',2),(3,8,'admin','Super livre !!','2014-12-25 23:33:21',10),(4,8,'admin','Pouet ?','2014-12-26 00:16:06',14),(5,0,'toto','pouet','2014-12-28 04:25:34',10),(6,0,'salut','abcdef !','2014-12-28 04:31:00',10),(7,8,'admin','Testons connecté !','2014-12-28 04:32:13',15),(8,0,'toto','testons pas connecté','2014-12-28 04:34:31',15),(9,0,'toto','retestons pas connecté','2014-12-28 04:34:47',15),(10,8,'admin','retestons connecté !','2014-12-28 04:35:24',15),(11,10,'julie','The best !','2014-12-28 05:57:04',4);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `idconfig` int(11) NOT NULL AUTO_INCREMENT,
  `opt` varchar(255) NOT NULL,
  `val` varchar(255) NOT NULL,
  PRIMARY KEY (`idconfig`),
  UNIQUE KEY `idconfig_UNIQUE` (`idconfig`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'gpwd','mmedNhpu');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `idnews` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idauthor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idnews`),
  UNIQUE KEY `idnews_UNIQUE` (`idnews`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (10,'Lorem ipsum dolor sit amet ~','<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p><p>(Pouet)</p><p>&#39;toto)</p>','2014-12-22 09:36:06','2014-12-28 01:56:40',8),(11,'Sed ut perspiciatis unde omnis','<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.</p><p>Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>','2014-12-26 13:30:44','2014-12-26 13:30:44',8),(12,'At vero eos et accusamus','<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus.</p><p>Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</p>','2014-12-26 13:36:49','2014-12-26 13:36:49',8),(13,'Toto pouet ~','<p>azertyuiopqsdfghjklmwxcvbn !</p>','2014-12-26 20:56:04','2014-12-26 20:56:04',8),(16,'test','<p>Test ?</p>','2014-12-28 14:30:52','2014-12-28 14:30:52',8);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newsletter`
--

DROP TABLE IF EXISTS `newsletter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newsletter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsletter`
--

LOCK TABLES `newsletter` WRITE;
/*!40000 ALTER TABLE `newsletter` DISABLE KEYS */;
INSERT INTO `newsletter` VALUES (12,'franeljulie@yahoo.fr');
/*!40000 ALTER TABLE `newsletter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(16) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `passwd` varchar(16) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isadmin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'admin','julie.franel@epitech.eu','toto','2014-12-21 20:31:08','2014-12-28 06:38:09',1),(10,'julie','franeljulie@yahoo.fr','toto','2014-12-28 05:56:44','2014-12-28 05:56:44',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-28 15:56:48
