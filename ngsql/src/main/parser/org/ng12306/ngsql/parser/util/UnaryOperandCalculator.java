package org.ng12306.ngsql.parser.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public interface UnaryOperandCalculator {
    Number calculate(Integer num);

    Number calculate(Long num);

    Number calculate(BigInteger num);

    Number calculate(BigDecimal num);
}
