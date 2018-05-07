USE [logintest]
GO

/****** Object:  Table [dbo].[cars]    Script Date: 2018-05-07 23:41:50 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[cars](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[owner] [nvarchar](50) NOT NULL,
	[brand] [nvarchar](50) NOT NULL,
	[year] [nchar](10) NOT NULL,
	[price] [int] NOT NULL,
	[forsale] [bit] NOT NULL,
	[description] [varchar](max) NULL,
 CONSTRAINT [PK_cars] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


