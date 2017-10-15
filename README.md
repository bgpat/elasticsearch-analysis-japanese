# elasticsearch-analysis-japanese

Elasticsearch analysis plugin for Japanese

## build

```shell
git clone --recurse-submodules https://github.com/bgpat/elasticsearch-analysis-japanese.git
cd elasticsearch-analysis-japanese
cd kuromoji
mvn clean package
cd ..
gradle
```

## install

```shell
$ELASTICSEARCH_HOME/bin/elasticsearch-plugin install build/distributions/elasticsearch-analysis-japanese.zip
```
