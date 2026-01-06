let create vehicle fleet =
  let new_fleet = 
    match fleet with
    | [] -> [vehicle]
    | header::rest -> header::(rest @ [vehicle])
  in
  new_fleet

let rec find id fleet =
  match fleet with
  | [] -> prerr_endline "Vehicule introuvable"; None
  | v::rest -> if Domain.get_id v = id then Some v else find id rest

let rec update id vehicle fleet =
  match fleet with
  | [] -> []
  | v::rest -> if Domain.get_id v = id then vehicle::rest else v::(update id vehicle rest)

let rec delete id fleet =
  match fleet with
  | [] -> []
  | v::rest -> if Domain.get_id v = id then rest else v::(delete id rest)