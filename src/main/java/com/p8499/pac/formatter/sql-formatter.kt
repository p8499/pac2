package com.p8499.pac.formatter

import gudusoft.gsqlparser.EDbVendor
import gudusoft.gsqlparser.TGSqlParser
import gudusoft.gsqlparser.pp.para.GFmtOpt
import gudusoft.gsqlparser.pp.para.GFmtOptFactory
import gudusoft.gsqlparser.pp.para.styleenums.TCaseOption
import gudusoft.gsqlparser.pp.para.styleenums.TCompactMode
import gudusoft.gsqlparser.pp.stmtformattor.FormattorFactory
import java.io.File

private var fmtOpt: GFmtOpt = GFmtOptFactory.newInstance().apply {
    caseKeywords = TCaseOption.CoNoChange
    caseIdentifier = TCaseOption.CoNoChange
    caseQuotedIdentifier = TCaseOption.CoNoChange
    caseFuncname = TCaseOption.CoNoChange
    caseDatatype = TCaseOption.CoNoChange
    compactMode = TCompactMode.Cpmugly
    lineWidth = 999
}

fun File.formatSql(database: String? = null) {
    val parser = TGSqlParser(when (database) {
        "oracle" -> EDbVendor.dbvoracle
        "postgresql" -> EDbVendor.dbvpostgresql
        else -> EDbVendor.dbvgeneric
    })
    parser.sqltext = readText()
    parser.parse()
    writeText(FormattorFactory.pp(parser, fmtOpt).trim())
}