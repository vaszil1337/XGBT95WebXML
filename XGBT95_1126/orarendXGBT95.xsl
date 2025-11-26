<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <orarend>
            <xsl:for-each select="/XGBT95_orarend/ora">
                <ora>
                    <xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
                    <xsl:attribute name="tipus"><xsl:value-of select="@tipus"/></xsl:attribute>
                    <targy><xsl:value-of select="targy"/></targy>
                    <idopont>
                        <nap><xsl:value-of select="idopont/nap"/></nap>
                        <tol><xsl:value-of select="idopont/tol"/></tol>
                        <ig><xsl:value-of select="idopont/ig"/></ig>
                    </idopont>
                    <helyszin><xsl:value-of select="helyszin"/></helyszin>
                    <oktato><xsl:value-of select="oktato"/></oktato>
                    <szak><xsl:value-of select="szak"/></szak>
                </ora>
            </xsl:for-each>
        </orarend>
    </xsl:template>

</xsl:stylesheet>
