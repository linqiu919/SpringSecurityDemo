/**
* BsNameMapper.java
*
* @author chenxb
* @date 2022-03-28 09:17:51
* @company 杭州震墨科技有限公司
*/
package com.example.securitydemo.mapper;

import com.example.securitydemo.entity.BsName;
import com.example.securitydemo.entity.BsNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsNameMapper {
    /**
     *  根据指定的条件删除数据库符合条件的记录
     *
     * @param example 查询条件
     * @return int 指定条件删除数据库符合条件的记录个数
     */
    int deleteByExample(BsNameExample example);

    /**
     *  根据主键删除数据库的记录
     *
     * @param id 主键
     * @return int 根据主键删除数据库的记录数
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  插入数据库记录
     *
     * @param record 一条数据记录
     * @return int 插入数据库记录成功个数
     */
    int insert(BsName record);

    /**
     *  动态字段,写入数据库记录
     *
     * @param record 一条数据记录
     * @return int 插入数据库记录成功个数
     */
    int insertSelective(BsName record);

    /**
     *  根据指定的条件查询符合条件的数据库记录
     *
     * @param example 查询条件
     * @return List<BsName> 根据指定的条件查询符合条件的数据库记录
     */
    List<BsName> selectByExample(BsNameExample example);

    /**
     *  根据指定主键获取一条数据库记录
     *
     * @param id 主键
     * @return BsName 根据指定主键获取一条数据库记录
     */
    BsName selectByPrimaryKey(Long id);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录
     *
     * @param record 一条数据记录
     * @param example 查询条件
     * @return int 更新符合条件的数据库记录成功个数
     */
    int updateByExampleSelective(@Param("record") BsName record, @Param("example") BsNameExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录
     *
     * @param record 一条数据记录
     * @param example 查询条件
     * @return int 更新符合条件的数据库记录成功个数
     */
    int updateByExample(@Param("record") BsName record, @Param("example") BsNameExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录
     *
     * @param record 一条数据记录
     * @return int 更新符合条件的数据库记录成功个数
     */
    int updateByPrimaryKeySelective(BsName record);

    /**
     *  根据主键来更新符合条件的数据库记录
     *
     * @param record 一条数据记录
     * @return int 更新符合条件的数据库记录成功个数
     */
    int updateByPrimaryKey(BsName record);
}