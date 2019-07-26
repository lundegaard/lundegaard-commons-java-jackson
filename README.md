# Lundegaard Java Commons - Jackson

This library is used for simple startup with object de/serialization to/from JSON using Jackson. 

## Usage

Just add it as dependency, e.g. into Maven (use the latest version)

```xml
<dependency>
    <groupId>eu.lundegaard.commons.java</groupId>
    <artifactId>jackson</artifactId>
    <version>3.0.0</version>
</dependency>
``` 

### De/serialization

You can use simple pre-configured object mapper via `SerializingUtil`. For serialization into JSON:

```java
MyObject myObject = new MyObject(...);
String json = SerializingUtil.serializeToJson(object);
```

For deserialization from JSON

```java
String json = ...
MyObject myObject = SerializingUtil.deserializeFromJson(json, MyObject.class);
```


### Custom ObjectMapper

If you need to customize the `ObjectMapper`, then obtain one using 

```java
ObjectMapper mapper = ObjectMapperFactory.createObjectMapper()
``` 

This mapper is standard pre-configured Jackson `ObjectMapper` and can be further customized. 

The mapper is thread-safe and thus should be shared and reused. **Best practice is storing it in singleton instance or similar**.


## Pre-configured object mapper

We use pre-configured Jackson `ObjectMapper`. This means that the mapper has registered Java 8 types (`Optional`, `java.time.*`, etc.) and is configured to match our most common needs (interoperability with our JS frontends, readable timestamps). For specific configuration, please refer to the `eu.lundegaard.commons.jackson.ObjectMapperFactory`.
