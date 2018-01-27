package org.creativebee.schedule.password.domain.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CityMapper {

	@Select("SELECT * FROM city WHERE id = #{id}")
	City selectCityById(@Param("id") long id);

}
