(* Exemple de fonction(s) utilisant la bibliothèque standard:
   https://v2.ocaml.org/api/Sys.html
   https://v2.ocaml.org/api/Unix.html
   https://v2.ocaml.org/api/Filename.html *)
let get_file filename =
  let exe_path = Sys.executable_name in
  let exe_dir = Filename.dirname exe_path in
  let full_path = Filename.concat exe_dir "data" in
  Filename.concat full_path filename
