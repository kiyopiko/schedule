package org.creativebee.schedule.password.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.creativebee.schedule.password.domain.City;

/**
 * @author Eddú Meléndez
 */
public interface CityMapper {

	@Select("SELECT * FROM city WHERE id = #{id}")
	City selectCityById(@Param("id") long id);

}
// @Component
// public class CityMapper {
//
// @Autowired
// private SqlSessionTemplate sqlSessionTemplate;
//
// public City selectCityById(long id) {
// return this.sqlSessionTemplate.selectOne("selectCityById", id);
// }
//
// }
