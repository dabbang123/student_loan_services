package com.sd.sls.guarantor.DAO;

/*
* Author: Nikunj Panchal
*/

import com.sd.sls.guarantor.model.Guarantor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sd.sls.constants.ISQLStatements;

import java.util.List;

@Repository
public class GuarantorDAO implements IGuarantorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Guarantor getGuarantorByAppId(int applicationId)
    {
        List<Guarantor> guarantorList = jdbcTemplate.query(ISQLStatements.FIND_GUARANTOR_BY_APPL_ID, new BeanPropertyRowMapper<>(Guarantor.class), new Object[] {applicationId});
        return guarantorList.size() > 0 ? guarantorList.get(0) : null;
    }
}

