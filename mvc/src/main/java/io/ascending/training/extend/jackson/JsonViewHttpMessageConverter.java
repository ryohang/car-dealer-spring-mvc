/**
 * 
 */
package io.ascending.training.extend.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.ascending.training.api.v1.BaseController;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author <a href="mailto:guoqing.huang@foxmail.com">Guoqing Huang</a>
 *
 * @since 2013-7-23
 * @version
 */
public class JsonViewHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	private static final ThreadLocal<Class<?>> jsonViewClassLocal = BaseController.jsonViewClassLocal;

	private static final ThreadLocal<MapperFeature> jsonViewMapperFeatureLocal = BaseController.jsonViewMapperFeatureLocal;

	private boolean prefixJson = false;

	@Override
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
		super.setPrefixJson(prefixJson);
	}

	@Override
	protected void writeInternal(Object object, Type type,HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		Class<?> jsonViewClass = getJsonViewClass();
		if(jsonViewClass != null) {
			ObjectMapper mapper = new ObjectMapper();
			JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
			JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
			try {
				if (this.prefixJson) {
					jsonGenerator.writeRaw("{} && ");
				}
				if (!disableMapperFeature_DEFAULT_VIEW_INCLUSION()) {
					mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
				}
				ObjectWriter objectWriter = mapper.writerWithView(jsonViewClass);
				objectWriter.writeValue(jsonGenerator, object);
			}
			catch (IOException ex) {
				throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
			}
		} else {
			super.writeInternal(object, type, outputMessage);
		}
	}

	private Class<?> getJsonViewClass() {
		return jsonViewClassLocal.get();
	}

	private boolean disableMapperFeature_DEFAULT_VIEW_INCLUSION() {
		boolean result = jsonViewMapperFeatureLocal.get() == null;
		jsonViewMapperFeatureLocal.remove();
		return result;
	}
}