let get_id vehicle = match vehicle with
  | id::_-> id
  | _ -> ""

let get_category vehicle = match vehicle with
  | _::category::_-> category
  | _ -> ""

let get_type vehicle = match vehicle with
  | _::_::_type::_-> _type
  | _ -> ""

let get_model vehicle = match vehicle with
  | _::_::_::model::_-> model
  | _ -> ""

let get_kilometers vehicle = match vehicle with
  | _::_::_::_::km::_-> km
  | _ -> ""



let print_vehicle vehicle = 
  match vehicle with
  | [] -> ()
  | id::category::_type::model::km_str::critical_str::_-> 
    Libcsv.print_readable [[id; category; _type; model; km_str; critical_str]]
  | _ -> ()

let is_critical vehicle =
  match vehicle with
  | id :: category :: _type :: model :: km :: _ ->
      Utils.safe_int km > Settings.critical
  | _ -> false

let update_critical_flag vehicle = 
  match vehicle with
  | id :: category :: _type :: model :: km ::  _ ->
    let critical = string_of_bool (is_critical vehicle) in
    [id; category;_type; model; km; critical]
  | _ -> failwith "Format incorrect"