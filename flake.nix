{
  description = "Template for a flake with a devShell";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }: flake-utils.lib.eachDefaultSystem (system:
    let
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShell = pkgs.mkShell {
        buildInputs = with pkgs; [
          openjdk11
          google-java-format
          nixpkgs-fmt
          lefthook

          # bazel
          bazel_5
          python3
        ];
      };
    });
}
