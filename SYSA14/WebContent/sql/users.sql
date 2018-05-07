USE [logintest]
GO

/****** Object:  Table [dbo].[users]    Script Date: 2018-05-07 23:44:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[users](
	[username] [varchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[firstName] [varchar](50) NOT NULL,
	[lastName] [varchar](50) NOT NULL
) ON [PRIMARY]
GO


