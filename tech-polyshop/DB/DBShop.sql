USE [master]
GO
/****** Object:  Database [PolyShopDB]    Script Date: 2/24/2023 12:24:06 PM ******/
CREATE DATABASE [PolyShopDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PolyShopDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PolyShopDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PolyShopDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PolyShopDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [PolyShopDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PolyShopDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PolyShopDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PolyShopDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PolyShopDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PolyShopDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PolyShopDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [PolyShopDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PolyShopDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PolyShopDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PolyShopDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PolyShopDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PolyShopDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PolyShopDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PolyShopDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PolyShopDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PolyShopDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PolyShopDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PolyShopDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PolyShopDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PolyShopDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PolyShopDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PolyShopDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PolyShopDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PolyShopDB] SET RECOVERY FULL 
GO
ALTER DATABASE [PolyShopDB] SET  MULTI_USER 
GO
ALTER DATABASE [PolyShopDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PolyShopDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PolyShopDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PolyShopDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PolyShopDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PolyShopDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'PolyShopDB', N'ON'
GO
ALTER DATABASE [PolyShopDB] SET QUERY_STORE = OFF
GO
USE [PolyShopDB]
GO
/****** Object:  Table [dbo].[accounts]    Script Date: 2/24/2023 12:24:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounts](
	[username] [varchar](30) NOT NULL,
	[password] [varchar](60) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 2/24/2023 12:24:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[category_id] [bigint] IDENTITY(1,1) NOT NULL,
	[category_name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 2/24/2023 12:24:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customers](
	[customer_id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[name_customer] [nvarchar](50) NOT NULL,
	[password] [varchar](20) NOT NULL,
	[phone] [varchar](20) NULL,
	[registered_date] [date] NULL,
	[role] [bit] NOT NULL,
	[status] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderdetail]    Script Date: 2/24/2023 12:24:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderdetail](
	[order_detail_id] [int] IDENTITY(1,1) NOT NULL,
	[quantity] [int] NOT NULL,
	[unit_price] [float] NOT NULL,
	[order_id] [int] NULL,
	[product_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 2/24/2023 12:24:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[amount] [float] NOT NULL,
	[order_date] [date] NULL,
	[status] [smallint] NOT NULL,
	[customer_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 2/24/2023 12:24:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[product_id] [bigint] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[discount] [float] NOT NULL,
	[entered_date] [date] NULL,
	[image] [varchar](200) NULL,
	[name_product] [nvarchar](100) NOT NULL,
	[quantity] [int] NOT NULL,
	[status] [smallint] NOT NULL,
	[unit_price] [float] NOT NULL,
	[category_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (1, N'áo hermec')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (2, N'quan hermec')
SET IDENTITY_INSERT [dbo].[categories] OFF
GO
SET IDENTITY_INSERT [dbo].[customers] ON 

INSERT [dbo].[customers] ([customer_id], [email], [name_customer], [password], [phone], [registered_date], [role], [status]) VALUES (2, N'nghiatd74@gmail.com', N'nghiatd', N'123', N'0397716555', CAST(N'2023-02-24' AS Date), 1, 1)
INSERT [dbo].[customers] ([customer_id], [email], [name_customer], [password], [phone], [registered_date], [role], [status]) VALUES (3, N'nghiaadssa@gmail.com', N'nghia', N'123', N'0398788888', NULL, 0, 2)
SET IDENTITY_INSERT [dbo].[customers] OFF
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([product_id], [description], [discount], [entered_date], [image], [name_product], [quantity], [status], [unit_price], [category_id]) VALUES (1, N'rách bét', 10, NULL, N'pe2a99d4d-5668-4f9e-a57a-2a2382e15e45.jpg', N'ao rach', 10, 1, 100000, 1)
SET IDENTITY_INSERT [dbo].[products] OFF
GO
ALTER TABLE [dbo].[orderdetail]  WITH CHECK ADD  CONSTRAINT [FK9iu7g1xs6c3u7n3ryo9yv2tyd] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([product_id])
GO
ALTER TABLE [dbo].[orderdetail] CHECK CONSTRAINT [FK9iu7g1xs6c3u7n3ryo9yv2tyd]
GO
ALTER TABLE [dbo].[orderdetail]  WITH CHECK ADD  CONSTRAINT [FKfxkmvpbfxqect54pd7slj4ll9] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[orderdetail] CHECK CONSTRAINT [FKfxkmvpbfxqect54pd7slj4ll9]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKpxtb8awmi0dk6smoh2vp1litg] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customers] ([customer_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKpxtb8awmi0dk6smoh2vp1litg]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FKog2rp4qthbtt2lfyhfo32lsw9] FOREIGN KEY([category_id])
REFERENCES [dbo].[categories] ([category_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FKog2rp4qthbtt2lfyhfo32lsw9]
GO
USE [master]
GO
ALTER DATABASE [PolyShopDB] SET  READ_WRITE 
GO
