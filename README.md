DataLinguist example
====================
This is a basic example of how to use [DataLinguist](https://github.com/simongray/datalinguist) in a REPL environment using the Clojure CLI.

To use the NLP pipeline as defined in the [example namespace](src/datalinguist_example.clj), the CoreNLP `models` library must also be included as a dependency _in addition_ to the `datalinguist` library itself, as this is where some of the necessary data for certain English annotators resides. See the [deps.edn file](deps.edn) for the specifics.

To annotate other languages you will likely also need a language-specific model, e.g. Chinese. See the [section on language models](https://github.com/simongray/datalinguist#language-models) in the DataLinguist readme for more.