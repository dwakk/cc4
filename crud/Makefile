SRC_OCAML = utils.ml parse_cli.ml libcsv.ml libunix.ml settings.ml domain.ml crud.ml fleet_manager.ml main.ml
#SRC_OCAML = parse_cli.ml libcsv.ml libunix.ml crud_csv.ml mainPersistance.ml

SOURCES = $(addprefix src/,$(SRC_OCAML))
PACKS = csv unix
RESULT = main
#RESULT = persist
all: byte-code

-include OCamlMakefile
