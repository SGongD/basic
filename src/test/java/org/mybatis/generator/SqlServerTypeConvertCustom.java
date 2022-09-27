package org.mybatis.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;


public class SqlServerTypeConvertCustom extends SqlServerTypeConvert implements ITypeConvert {
    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains("tinyint")) {
            return DbColumnType.INTEGER;
        } else if (t.contains("decimal")) {
            return DbColumnType.BIG_DECIMAL;
        } else if (fieldType.toLowerCase().contains("datetime")) {
            return DbColumnType.DATE;
        }
        return super.processTypeConvert(globalConfig, fieldType);
    }

    /**
     * 유형 변환 수행
     *
     * @param globalConfig 전역 구성
     * @param tableField   필드 열 정보
     * @return ignore
     */
    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
        String t = tableField.getName().toLowerCase();
        if (t.contains("is_deleted")) {
            return DbColumnType.BOOLEAN;
        }
        return processTypeConvert(globalConfig, tableField.getType());
    }
}
