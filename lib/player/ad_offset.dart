class AdOffset {
  final String offset;

  AdOffset(this.offset);

  factory AdOffset.preroll() => AdOffset("pre");
  factory AdOffset.postroll() => AdOffset("post");
  factory AdOffset.custom({
    int? hours,
    int? minutes,
    int? seconds,
    int? miliseconds,
  }) =>
      AdOffset(
        "${hours ?? 00}:${minutes ?? 00}:${seconds ?? 00}:${miliseconds ?? 00}",
      );

  Map<String, dynamic> toJson() => {
        "offset": offset,
      };

  factory AdOffset.fromJson(Map<String, dynamic> json) => AdOffset(
        json["offset"] ?? "",
      );
}
