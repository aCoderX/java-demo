package com.acoderx.demo.commons.test.junit;

import com.acoderx.demo.commons.test.SubClass;
import com.acoderx.demo.commons.test.Sum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Description:junit4+Mockito 单元测试
 *
 * @author xudi
 * @since 2019-12-04
 */
@RunWith(MockitoJUnitRunner.class)
public class JSumTest {
    @InjectMocks
    private Sum sum;

    @Mock
    private SubClass subClass;


    @Test
    public void test() {
        Mockito.when(subClass.getRandomC())
                .thenReturn(3);
        int sum = this.sum.sum(1, 2);
        Assert.assertEquals(6, sum);
    }
}
