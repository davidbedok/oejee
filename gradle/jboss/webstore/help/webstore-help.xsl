<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ts="urn:hu:qwaevisz:schema:xml:webstore:help:1">

<xsl:output method="html"/>

<xsl:template match="/">
	<html> 
		<head>
			<link type="text/css" href="webstore-help.css" rel="stylesheet" />
			<script type="text/javascript" src="webstore-help.js"></script>
		</head>
		<body>
			<xsl:apply-templates />
		</body>
	</html>
</xsl:template>

<xsl:template match="ts:webstore">
	<h1>WebStore Interface Help</h1>
	<xsl:apply-templates select="ts:service" />
</xsl:template>

<xsl:template match="ts:service">
	<h2><xsl:value-of select="@name" /></h2>
	<div><xsl:value-of select="@description" /></div>
	<table>
		<xsl:for-each select="ts:operation">
			<tr>
				<td>
					<span>
						<xsl:attribute name="onclick">
							javascript: showDescription('<xsl:value-of select="@description"/>')
						</xsl:attribute>
						<strong><xsl:value-of select="@name"/></strong>
					</span>
					(
					<xsl:for-each select="ts:argument">
						<span>
							<xsl:attribute name="title">
								<xsl:value-of select="text()"/>
							</xsl:attribute>						
							<xsl:value-of select="@type"/><xsl:text> </xsl:text>
							<xsl:value-of select="@name"/>
						</span>
						<xsl:choose>
							<xsl:when test="position() != last()">,</xsl:when>
						</xsl:choose>
					</xsl:for-each>
					)
				</td>
				<td>
					<xsl:value-of select="@description"/>
				</td>
			</tr>
		</xsl:for-each>
	</table>
</xsl:template>

</xsl:stylesheet>