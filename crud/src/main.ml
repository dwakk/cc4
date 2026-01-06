let () = 

  let _, options, _ = Parse_cli.get_args () in
  let fleet = Fleet_manager.load_fleet () in

  match options with
  | "-C" :: args -> 
    let vehicle = args in
    Fleet_manager.create_vehicle vehicle fleet;
    print_endline ("Véhicule créé avec succès");
  | "-R" :: "-critical" :: _ ->
    let fleet = Fleet_manager.find_all_critical fleet in
    Fleet_manager.list_vehicles fleet;
  | "-R" :: _ -> Fleet_manager.list_vehicles fleet;
  | "-U" :: id :: args ->
    let vehicle = id :: args in
    Fleet_manager.update_vehicle id vehicle fleet;
    print_endline ("Véhicule modifié avec succès");
  | "-D" :: id :: _ ->
    Fleet_manager.delete_vehicle id fleet;
    print_endline ("Véhicule supprimé avec succès");
  | _ -> 
    prerr_endline "Commande inconnue";
