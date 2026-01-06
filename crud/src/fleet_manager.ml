let csv_file = Libunix.get_file "flotte.csv"

let load_fleet () =
  Libcsv.load_csv csv_file

let save_fleet fleet =
  Libcsv.save_csv csv_file fleet

let list_vehicles fleet =
  match fleet with
  | [] -> ()
  | header :: rest -> ignore (Libcsv.print_readable rest)


let create_vehicle vehicle fleet =
  if List.exists (fun v -> Domain.get_id v = Domain.get_id vehicle) fleet then begin
    prerr_endline "Le vehicule existe deja";
    exit 1
  end else begin
    let vehicle = Domain.update_critical_flag vehicle in
    let new_fleet = Crud.create vehicle fleet in
    save_fleet new_fleet;
  end

let get_vehicle id fleet =
  Crud.find id fleet

let update_vehicle id vehicle fleet =
  if List.exists (fun v -> Domain.get_id v = id) fleet then begin
    let vehicle = Domain.update_critical_flag vehicle in
    let new_fleet = Crud.update id vehicle fleet in
    save_fleet new_fleet;
  end else begin
    prerr_endline "Le vehicule n'existe pas";
    exit 1
  end
let delete_vehicle id fleet =
  let new_fleet = Crud.delete id fleet in
  save_fleet new_fleet

let find_all_critical fleet =
  match fleet with
  | [] -> []
  | header :: rest ->
      let criticals = List.filter Domain.is_critical rest in
      if criticals = [] then (
      []
      ) else
        header :: criticals