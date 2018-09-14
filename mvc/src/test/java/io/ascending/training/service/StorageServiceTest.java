package io.ascending.training.service;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class StorageServiceTest extends ServiceTest {
    @InjectMocks
    @Autowired
    private StorageService storageService;

    @Value("#{ applicationProperties['amazon.s3.bucket'] }")
    protected String s3Bucket;

    @Mock
    private AmazonS3 client=Mockito.mock(AmazonS3.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        validateMockitoUsage();
    }

    @Test
    public void getObject() {
        String key = "testKey";
        storageService.getObject(key);
        verify(client, times(1)).getObject(s3Bucket, key);
        String key2=null;
        storageService.getObject(key2);
        verify(client, times(1)).getObject(s3Bucket, key);
    }
}
