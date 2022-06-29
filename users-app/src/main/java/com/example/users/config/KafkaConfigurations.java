package com.example.users.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfigurations {
	
	/*
	 * Consumer
	 * Se van a definir las propiedades para poder consumir los mensajes de kafka
	 */
	@Bean
	public Map<String, Object> consumerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group"); //identificador de consumidor o productor (este caso es de consumidor de msj)
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); //Si se van a hacer auto commit de los msj recibidos
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100"); // cada cuantos milisegundos se hace el autocomit
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000"); //tiempo de vida de la sesion en milisegundos
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class); 
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); //Los dos ultimos es para indicar que la llave-valor del mensaje enviado a kafka va a ser Integer-String (props es un map)
		return props;
	}
	
	//Creando un kafka consumer factory para poder crear el listener
	@Bean
	public ConsumerFactory<Integer, String> consumerFactory() {
	return new DefaultKafkaConsumerFactory<>(consumerProps());
	}
	
	//Creando un kafka listener para permitir recibir y hacer algo con los mensajes de kafka
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	
	/*
	 * Producer
	 */
	private Map<String, Object> producerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.RETRIES_CONFIG, 0); //Configurando los reintentos
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); //En lugar de mandar msj x msj creo un batch que junta mensajes durante 16384milisegundos y los mando todos juntos
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); //Define cuantos batch voy a tener cuando estoy haciendo el cache en memoria
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return props;
	}

	@Bean
	public KafkaTemplate<Integer, String> createTemplate() {
		Map<String, Object> senderProps = producerProps();
		ProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<Integer, String>(senderProps);
		KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf);
		return template;
	}
}
