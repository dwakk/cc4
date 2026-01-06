let safe_int s =
  try int_of_string s
  with Failure _ -> 0

let safe_float s =
  try float_of_string s
  with Failure _ -> 0.

let safe_bool s =
  try bool_of_string s
  with Failure _ -> false